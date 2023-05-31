<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
<head>
  <link rel="stylesheet" href="https://cdn.datatables.net/1.13.4/css/jquery.dataTables.css" />

  <script type="text/javascript" src="https://code.jquery.com/jquery-3.5.1.js"></script>
  <script src="https://cdn.datatables.net/1.13.4/js/jquery.dataTables.js"></script>
</head>
<center>
  <h1 style="color: green;">
    DataTable
  </h1>
</center>

<table id="tableID" class="display">
  <thead>
  <tr>
    <th>FirstName</th>
    <th>LastName</th>
    <th>Age</th>
  </tr>
  </thead>
</table>

<form id="form">
  <label>First Name: </label><input id="fname" type="text">
  <label>Last Name: </label><input id="lname" type="text">
  <label>Age: </label><input id="age" type="text">
  <button type="submit">Submit</button>
</form>
<div id="div1">
  <s:property value="profiles" />
 </div>

<script>
  $(document).ready(function () {
    let data = $("#div1").text()
    $("#div1").hide()
    data = JSON.parse(data)
    console.log(data)

    $("#form").on("submit", function(event){
      let fname = $("#fname").val();
      let lname = $("#lname").val();
      let age = $("#age").val();

      $.post("http://localhost:8080?firstname="+fname+"&lastname="+lname+"&age="+age, {}, function(data){
      });
    });

    $('#tableID').dataTable({
      "data": data.data
    });
  });
</script>
</body>
</html>
