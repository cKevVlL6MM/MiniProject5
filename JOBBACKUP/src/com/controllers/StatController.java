package com.controllers;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.type.BigDecimalType;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.beans.HibernateUtil;
import com.beans.ProfileUtilisateur;
import com.googlecode.charts4j.AxisLabels;
import com.googlecode.charts4j.AxisLabelsFactory;
import com.googlecode.charts4j.AxisStyle;
import com.googlecode.charts4j.AxisTextAlignment;
import com.googlecode.charts4j.BarChart;
import com.googlecode.charts4j.BarChartPlot;
import com.googlecode.charts4j.Color;
import com.googlecode.charts4j.Data;
import com.googlecode.charts4j.Fills;
import com.googlecode.charts4j.GCharts;
import com.googlecode.charts4j.LinearGradientFill;
import com.googlecode.charts4j.PieChart;
import com.googlecode.charts4j.Plots;
import com.googlecode.charts4j.Slice;

import hibernate.model.TableStatistique;

@Controller
@RequestMapping(value="/stat")
public class StatController {
	
	
	
	
	@RequestMapping(method = RequestMethod.GET)
	public static ModelAndView doGet(HttpServletRequest request,ModelMap model)
	{
		ProfileUtilisateur pl = (ProfileUtilisateur)  request.getSession().getAttribute("profileutilisateur");
		
		if( pl!=null && pl.isAdmin() )
		{
			SessionFactory sf =  HibernateUtil.getSessionFactory();
			//SessionFactory sessionFactory = createSessionFactory();
			Session sessions= sf.openSession();
			
			Query query=sessions.createSQLQuery("select * FROM Table_Statistique WHERE DATESTAT = (select max(DATESTAT) FROM TABLE_STATISTIQUE)").addEntity(TableStatistique.class);
			
			int percentEleve=0;
			int percentEntreprise=0;
			int nombreInscrits=0;
			int nombreOffres =0;
			if(!query.list().isEmpty())
			{
				
				TableStatistique t = (TableStatistique) query.list().get(0);
				nombreInscrits = BigDecimaltoInt(t.getNombreinscrits());
				int nombreEleves =  BigDecimaltoInt(t.getNombreeleves());
				int nombreEnterprises = BigDecimaltoInt(t.getNombreentreprise());
				nombreOffres = BigDecimaltoInt(t.getNombreoffres());
				
						if(nombreInscrits!=0)
						{
							 percentEleve= (nombreEleves / nombreInscrits)*100;
							percentEntreprise = (nombreEnterprises / nombreInscrits)*100;
							
									
						}
						
						
					
			}
			
			//creation diagramme pie pour inscriptions
			Slice s1 = Slice.newSlice(percentEleve, Color.newColor("CACACA"),"Eleves "+percentEleve+" %");
			Slice s2 = Slice.newSlice(percentEntreprise, Color.newColor("951800"),"Enterprises "+percentEntreprise+" %");
			PieChart chart = GCharts.newPieChart(s1, s2);
	        chart.setTitle("Nombre des Inscrits");
	        chart.setSize(500, 200);
	        
	        
	        model.addAttribute("nombreOffres", nombreOffres);
			model.addAttribute("nombreInscrits", nombreInscrits );
	        model.addAttribute("inscritsURL",chart.toURLString());
	        
	        
	        
	        //stat mois par mois

			Query query2=sessions.createSQLQuery("select to_char(DATESTAT,'Mon-yyyy') \"Date\", sum(NOMBREOFFRES) \"NombreOffres\", sum(NOMBREELEVES) \"NombreEleves\", sum(NOMBREENTREPRISE) \"NombreEntreprises\", sum(NOMBREINSCRITS) as \"NombreInscrits\"  from TABLE_STATISTIQUE group by to_char(DATESTAT,'Mon-yyyy') order by to_char(DATESTAT,'Mon-yyyy') ")
					.addScalar("NombreInscrits", LongType.INSTANCE)
					.addScalar("NombreOffres", LongType.INSTANCE)
					.addScalar("NombreEleves", LongType.INSTANCE)
					.addScalar("NombreEntreprises",LongType.INSTANCE)
					.addScalar("Date",StringType.INSTANCE)
					;
			
			if(!query2.list().isEmpty())
			{
				List<Object[]> rows = query2.list();
				
				List<List<Integer>> datalist = new ArrayList();
				List<String> MonthList = new ArrayList();
				
				for(int q=0; q<query2.list().size();q++)
				{
					MonthList.add(rows.get(q)[4].toString());
					System.out.println(rows.get(q)[4].toString());
				}
				
				
				
				
				for(int m=0;m<4;m++)
				{
					List<Integer> dataForOne =new ArrayList();
				for(int g=0; g<query2.list().size();g++)
				{
					dataForOne.add(Integer.parseInt((rows.get(g)[m].toString()) )*10);	
					System.out.println(Integer.parseInt((rows.get(g)[m].toString() )));
				}
				datalist.add(dataForOne);
				}
				
				
				//on cree les datasets pour chaque mois
				
				for(int j=0;j<rows.size();j++)
				{
					
					
				}
				
				
				
				
				
				
				BarChartPlot BarInscrits = Plots.newBarChartPlot(Data.newData(datalist.get(0)), Color.AQUAMARINE, "Inscrits");
				BarChartPlot BarOffers = Plots.newBarChartPlot(Data.newData(datalist.get(1)), Color.CHOCOLATE , "Offres");	
				BarChartPlot BarEleves = Plots.newBarChartPlot(Data.newData(datalist.get(2)), Color.TOMATO , "Eleves");		
				BarChartPlot BarEntreprises = Plots.newBarChartPlot(Data.newData(datalist.get(3)), Color.GRAY , "Entreprises");				
				BarChart chartHistoriques = GCharts.newBarChart(BarInscrits, BarOffers , BarEleves, BarEntreprises);
				
				 AxisLabels Nombre = AxisLabelsFactory.newAxisLabels("Nombre", 10);
				 AxisStyle axisStyle = AxisStyle.newAxisStyle(Color.BLACK, 13, AxisTextAlignment.CENTER);
				 AxisLabels Mois = AxisLabelsFactory.newAxisLabels("Mois", 10);
				 Nombre.setAxisStyle(axisStyle);
				 Mois.setAxisStyle(axisStyle);
				 
				 chartHistoriques.addXAxisLabels(AxisLabelsFactory.newAxisLabels(MonthList));
				 chartHistoriques.addYAxisLabels(AxisLabelsFactory.newNumericRangeAxisLabels(0, 10, 1));
				 chartHistoriques.addYAxisLabels(Nombre);
				 chartHistoriques.addXAxisLabels(Mois);

				 chartHistoriques.setSize(400, 450);
				 chartHistoriques.setBarWidth(100);
				 chartHistoriques.setSpaceWithinGroupsOfBars(20);
				 chartHistoriques.setDataStacked(true);
				 chartHistoriques.setTitle("Statistiques mois par mois", Color.BLACK, 16);
				 chartHistoriques.setGrid(100, 10, 3, 2);
				 chartHistoriques.setBackgroundFill(Fills.newSolidFill(Color.WHITE));
			        LinearGradientFill fill = Fills.newLinearGradientFill(0, Color.LAVENDER, 100);
			        fill.addColorAndOffset(Color.WHITE, 0);
			        chartHistoriques.setAreaFill(fill);
			        String urlhistorique = chartHistoriques.toURLString();
			       
				 
			        
			        
			        
			        
			        
				 model.addAttribute("charthistoriquesURL", urlhistorique);
				 
				 
					
				
				
				
			}
			
			
	        
	        
	        
	        
	        	return new ModelAndView("statistiques",model);
				
			}
			
			
			
			
			
		
		
		return  new ModelAndView("Login");
		
		
		}
	

private static int BigDecimaltoInt(BigDecimal bd)
{
	
	int d = bd.intValueExact();
	return d;// The double you want
}
	

}
