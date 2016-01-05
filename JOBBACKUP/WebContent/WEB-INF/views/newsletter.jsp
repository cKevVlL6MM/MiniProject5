<!DOCTYPE HTML>
<html>
<head>
<link rel="shortcut icon" type="image/x-icon" href="jobin.ico" />
<title>Newsletter</title>
</head>
<body>
<center>
<h1>Envoyer la newsletter</h1>
</center>


<form method="post" action="newsletter">
Sujet : <input name="sujet" rows="1" cols="50"/><br/>
<textarea name="contenu" rows="10" cols="50">Contenu de la newsletter</textarea><br/>
A quelle promotion envoyer la newsletter :<br/>
CPI 1 <input type="checkbox" name="cpi1"/><br/>
CPI 2 <input type="checkbox" name="cpi2"/><br/>
ING 1 <input type="checkbox" name="ing1"/><br/>
ING 2 <input type="checkbox" name="ing2"/><br/>
ING 3 <input type="checkbox" name="ing3"/><br/>
<input type="submit" value="Envoyer"/>
</form>

</body>
</html>