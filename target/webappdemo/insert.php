<html>

<body>

<?php

$con = mysql_connect("jdbc:mariadb://noelvaes.eu/StudentDB,student,student123");

if (!$con)

  {

  die('Could not connect: ' . mysql_error());

  }

mysql_select_db("student", $con);

$sql="INSERT INTO GuestBook (Name, Message)

VALUES

('$_POST[Name]','$_POST[Message]')";

if (!mysql_query($sql,$con))

  {

  die('Error: ' . mysql_error());

  }

echo "1 record added";



mysql_close($con)

?>

</body>

</html>