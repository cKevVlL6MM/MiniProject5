package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import com.googlecode.charts4j.AxisLabels;
import com.googlecode.charts4j.AxisLabelsFactory;
import com.googlecode.charts4j.AxisStyle;
import com.googlecode.charts4j.AxisTextAlignment;
import com.googlecode.charts4j.BarChart;
import com.googlecode.charts4j.BarChartPlot;
import com.googlecode.charts4j.Color;
import com.googlecode.charts4j.Data;
import com.googlecode.charts4j.DataUtil;
import com.googlecode.charts4j.Fills;
import com.googlecode.charts4j.GCharts;
import com.googlecode.charts4j.LinearGradientFill;
import com.googlecode.charts4j.Plots;

import static com.googlecode.charts4j.Color.*;
import static com.googlecode.charts4j.UrlUtil.normalize;
import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.BeforeClass;



public class testCharts {

	// il s'agit un test adapt� d'une proc�dure de test publi� par le cr�ateur de la librairie charts4j
	@BeforeClass
    public static void setUpBeforeClass() throws Exception {
        Logger.global.setLevel(Level.ALL);
    }

    @Test
    public void BarChartTest() {
       
        BarChartPlot team1 = Plots.newBarChartPlot(Data.newData(25, 43, 12, 30), BLUEVIOLET, "Team A");
        BarChartPlot team2 = Plots.newBarChartPlot(Data.newData(8, 35, 11, 5), ORANGERED, "Team B");
        BarChartPlot team3 = Plots.newBarChartPlot(Data.newData(10, 20, 30, 30), LIMEGREEN, "Team C");

       
        BarChart chart = GCharts.newBarChart(team1, team2, team3);

       
        AxisStyle axisStyle = AxisStyle.newAxisStyle(BLACK, 13, AxisTextAlignment.CENTER);
        AxisLabels score = AxisLabelsFactory.newAxisLabels("Score", 50.0);
        score.setAxisStyle(axisStyle);
        AxisLabels year = AxisLabelsFactory.newAxisLabels("Year", 50.0);
        year.setAxisStyle(axisStyle);

        
        chart.addXAxisLabels(AxisLabelsFactory.newAxisLabels("2002", "2003", "2004", "2005"));
        chart.addYAxisLabels(AxisLabelsFactory.newNumericRangeAxisLabels(0, 100));
        chart.addYAxisLabels(score);
        chart.addXAxisLabels(year);

        chart.setSize(600, 450);
        chart.setBarWidth(100);
        chart.setSpaceWithinGroupsOfBars(20);
        chart.setDataStacked(true);
        chart.setTitle("Team Scores", BLACK, 16);
        chart.setGrid(100, 10, 3, 2);
        chart.setBackgroundFill(Fills.newSolidFill(ALICEBLUE));
        LinearGradientFill fill = Fills.newLinearGradientFill(0, LAVENDER, 100);
        fill.addColorAndOffset(WHITE, 0);
        chart.setAreaFill(fill);
        String url = chart.toURLString();
        
        Logger.global.info(url);
        String expectedString = "http://chart.apis.google.com/chart?chf=bg,s,F0F8FF|c,lg,0,E6E6FA,1.0,FFFFFF,0.0&chs=600x450&chd=e:QAbhHrTN,FIWZHCDN,GaMzTNTN&chtt=Team+Scores&chts=000000,16&chg=100.0,10.0,3,2&chxt=y,y,x,x&chxr=0,0.0,100.0|1,0.0,100.0|3,0.0,100.0&chxl=1:|Score|2:|2002|2003|2004|2005|3:|Year&chxp=1,50.0|3,50.0&chxs=1,000000,13,0|3,000000,13,0&chdl=Team+A|Team+B|Team+C&chco=8A2BE2,FF4500,32CD32&chbh=100,20,8&cht=bvs";
        assertEquals("Junit error", normalize(expectedString), normalize(url));
    }

    @Test
    public void ChartTest() {
        
        final int MAX_MEDALS = 51;
        Data goldData= DataUtil.scaleWithinRange(0, MAX_MEDALS, Arrays.asList(MAX_MEDALS, 36, 23, 19, 16));
        Data silverData= DataUtil.scaleWithinRange(0, MAX_MEDALS, Arrays.asList(21, 38, 21, 13, 10));
        Data bronzeData= DataUtil.scaleWithinRange(0, MAX_MEDALS, Arrays.asList(28, 36, 28, 15, 15));
        BarChartPlot gold = Plots.newBarChartPlot(goldData, GOLD, "Gold");
        BarChartPlot silver = Plots.newBarChartPlot(silverData, SILVER, "Silver");
        BarChartPlot bronze = Plots.newBarChartPlot(bronzeData, Color.BROWN, "Bronze");
        BarChart chart = GCharts.newBarChart(gold, silver,  bronze);

       
        AxisStyle axisStyle = AxisStyle.newAxisStyle(BLACK, 13, AxisTextAlignment.CENTER);
        AxisLabels country = AxisLabelsFactory.newAxisLabels("Country", 50.0);
        country.setAxisStyle(axisStyle);
        AxisLabels countries = AxisLabelsFactory.newAxisLabels("Germany", "United Kingdom", "Russia", "USA", "China");
        countries.setAxisStyle(axisStyle);
        AxisLabels medals = AxisLabelsFactory.newAxisLabels("Medals", 50.0);
        medals.setAxisStyle(axisStyle);
        AxisLabels medalCount = AxisLabelsFactory.newNumericRangeAxisLabels(0, MAX_MEDALS);
        medalCount.setAxisStyle(axisStyle);


       
        chart.addXAxisLabels(medalCount);
        chart.addXAxisLabels(medals);
        chart.addYAxisLabels(countries);
        chart.addYAxisLabels(country);
        chart.addTopAxisLabels(medalCount);
        chart.setHorizontal(true);
        chart.setSize(450, 650);
        chart.setSpaceBetweenGroupsOfBars(30);

        chart.setTitle("2008 Beijing Olympics Medal Count", BLACK, 16);
       
        chart.setGrid((50.0/MAX_MEDALS)*20, 600, 3, 2);
        chart.setBackgroundFill(Fills.newSolidFill(LIGHTGREY));
        LinearGradientFill fill = Fills.newLinearGradientFill(0, Color.newColor("E37600"), 100);
        fill.addColorAndOffset(Color.newColor("DC4800"), 0);
        chart.setAreaFill(fill);
        String url = chart.toURLString();
        

        Logger.global.info(url);
        String expectedString = "http://chart.apis.google.com/chart?chf=bg,s,D3D3D3|c,lg,0,E37600,1.0,DC4800,0.0&chs=450x650&chd=e:..tLc3X2UF,aWvraWQUMj,jItLjIS0S0&chtt=2008+Beijing+Olympics+Medal+Count&chts=000000,16&chg=19.6078431372549,600.0,3,2&chxt=y,y,t,x,x&chxl=0:|Germany|United+Kingdom|Russia|USA|China|1:|Country|4:|Medals&chxs=0,000000,13,0|1,000000,13,0|2,000000,13,0|3,000000,13,0|4,000000,13,0&chxp=1,50.0|4,50.0&chxr=1,0.0,100.0|2,0.0,51.0|3,0.0,51.0|4,0.0,100.0&chdl=Gold|Silver|Bronze&chco=FFD700,C0C0C0,A52A2A&chbh=23,4,30&cht=bhg";
        assertEquals("Junit error", normalize(expectedString), normalize(url));
    }

	
	

}
