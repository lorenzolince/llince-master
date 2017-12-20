<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
    <head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
             <script src="resources/js/xml2json.js"></script>
             <script src="resources/js/xml2json.min.js"></script>
               <script type="text/javascript">
              var host=window.location.origin;  
              var lista;
               </script>
    </head>
    <body bgcolor="#DFEBEB">
        
        <div>
    <table id="tblCustomers" border="0" cellpadding="0" cellspacing="0">
        <thead>
            <tr bgcolor="#DBECB8">
                <th>
                    CustomerId :
                </th>
                <th>
                    Name :
                </th>
                <th>
                    Country :
                </th>
                <th>
                </th>
                <th>
                </th>
            </tr>
        </thead>
        <tbody>
            <tr >
                <td class="CustomerId">
                    <span></span>
                </td>
                <td class="Name">
                    <span></span>
                    <input type="text"  style="background-color:#DFEBEB" id="txtEditName" class="Hide" />
                </td>
                <td class="Country">
                    <span></span>
                    <input type="text"  style="background-color:#DFEBEB" id="txtEditCountry" class="Hide" />
                </td>
                <td>
                    
                    <input type="button" id="btnUpdate" value="Update" class="Hide Update" />
                </td>
            </tr>
        </tbody>
        <tfoot>
            <table border="1" cellpadding="0" cellspacing="0" style="border-collapse: collapse">
                <tr bgcolor="#DBECB8">
                    <td style="width: 150px">
                        Name:<br />
                         <input type="text"  style="background-color:#DFEBEB" id="txtName" Width="140" class="Hide" />
                    </td>
                    <td style="width: 150px">
                        Country:<br />
                        <input type="text" style="background-color:#DFEBEB" id="txtCountry" Width="140" class="Hide" />
                    </td>
                    <td style="width: 100px">
                        <br />
                        <input type="button" id="btnAdd" value="Add" class="Hide Add" />
                    </td>
                </tr>
            </table>
        </tfoot>
    </table>
     <div id='TextBoxesGroup'>
        <div id='TextBoxDiv'>
       </div>
    </div>            
    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
    <script type="text/javascript">
        $(function () {
            $.ajax({
                type: "GET",
                url: host+"/llince-rest-spring-security/service/rest/listCustomer",
                data: '{}',
                crossDomain: true,
                contentType: "application/json; charset=utf-8",
                dataType: "json",
                success:  function(data) {
				console.log("SUCCESS: ", data);
                               OnSuccess(data);
			}
            });
        });
 
        function OnSuccess(response) {
           
            var str = JSON.stringify(response);
           lista = JSON.parse(str);
          var customer;
          var row = $("[id*=tblCustomers] > tbody tr:last-child").clone(true);
            $("[id*=tblCustomers] tr").not(':has(th)').remove();
            customer =  lista[0];
            AppendRow(row, customer.customerId, customer.name, customer.country);
                row = $("[id*=tblCustomers] > tbody tr:last-child").clone(true);
            listarCustomer(lista);
        }
 
        function AppendRow(row, customerId, name, country) {
            //Bind CustomerId.
            $(".CustomerId", row).find("span").html(customerId);
 
            //Bind Name.
            $(".Name", row).find("span").html(name);
            $(".Name", row).find("input").val(name);
 
            //Bind Country.
            $(".Country", row).find("span").html(country);
            $(".Country", row).find("input").val(country);
            $("[id*=tblCustomers]").append(row);
        }
             function listarCustomer(lista) {
            //[id*=tblCustomers]
           // alert("1");
        $('#TextBoxDiv').remove();
        var html = '<div class="diviciones">' +
                '<div align="center">' +
                '<div style="text-align:left; width: 70%; margin-left: -1.7em;">' +
                '<table width="100%" class="table">' +
                '<tr bgcolor="#DBECB8">' +
                '<td width="12%">customerId</td>' +
                '<td width="12%">Name</td>' +
                ' <td width="12%">Contry</td>' +
                ' <td width="12%">Update</td>' +
                ' <td width="12%">Delete</td>' +
                ' </tr>';
       var  customer;
        for (var idx in lista) {
         customer  = lista[idx];
           html = html + '<tr><td>' + customer.customerId + '</td><td>' + customer.name+'</td><td>' + customer.country+'</td>'
           + '<td>' + '<a href="javascript:UpdateCutomer(\'' + idx + '\');"> ' + '<img src="resources/images/update.jpg" width="22" height="22" style="padding-left:0px;"/></a></td>'
           + '<td>' + '<a href="javascript:DeleteCutomer(\'' + idx + '\');"> ' + '<img src="resources/images/eliminar.png" width="22" height="22" style="padding-left:0px;"/></a></td></tr>';
        }
         html = html + '</table></div>';
    var txt3 =  $(document.createElement('div')) .attr("id", 'TextBoxDiv');
    
    $("#TextBoxesGroup").append(txt3); 
    $("#TextBoxDiv").append(html);
    }
        //Add event handler.
        $("body").on("click", "[id*=btnAdd]", function () {
            
            var txtName = $("[id*=txtName]");
            var txtCountry = $("[id*=txtCountry]");
            if (txtName.val() !== '' && txtCountry.val() !== '') {
              var customer2={};
              customer2['customerId'] = 1;
              customer2['name'] = txtName.val();
              customer2['country'] = txtCountry.val();
             var datos = JSON.stringify(customer2);
                $.ajax({
                    type: "POST",
                    url: host+"/llince-rest-spring-security/service/rest/saveCustomer",
                    data: datos,
                    contentType: "application/json; charset=utf-8",
                    dataType: "json",
                    success: function (response) {
                 var str = JSON.stringify(response);
                 var lista = JSON.parse(str);
                var customer;
                var row = $("[id*=tblCustomers] > tbody tr:last-child").clone(true);
                 $("[id*=tblCustomers] tr").not(':has(th)').remove();
                 customer =  lista[0];
                 AppendRow(row, customer.customerId, customer.name, customer.country);
                row = $("[id*=tblCustomers] > tbody tr:last-child").clone(true);
                listarCustomer(lista);
                        txtName.val("");
                        txtCountry.val("");
                    }
                });
            } else {
                alert('Please Enter both Name and Country');
            }
            return false;
        });
 
      
        //Update event handler.
        $("body").on("click", "[id*=tblCustomers] .Update", function () {
            var row = $(this).closest("tr");
            $("td", row).each(function () {
                if ($(this).find("input").length > 0) {
                    var span = $(this).find("span");
                    var input = $(this).find("input");
                    span.html(input.val());
                }
            });
           
            var customerId = row.find(".CustomerId").find("span").html();
            var name = row.find(".Name").find("span").html();
            var country = row.find(".Country").find("span").html();
            var customer={};
              customer['customerId'] = customerId;
              customer['name'] = name;
              customer['country'] = country;
             var data = JSON.stringify(customer);
            $.ajax({
                type: "POST",
                url: host+"/llince-rest-spring-security/service/rest/updteCustomer",
                data: data,
                crossDomain: true,
                contentType: "application/json; charset=utf-8",
                dataType: "json",
                success:  function OnSuccess(response) {
                   var str = JSON.stringify(response);
                    lista = JSON.parse(str);
                  var customer;
          var row = $("[id*=tblCustomers] > tbody tr:last-child").clone(true);
            $("[id*=tblCustomers] tr").not(':has(th)').remove();
            customer =  lista[0];
            AppendRow(row, customer.customerId, customer.name, customer.country);
                row = $("[id*=tblCustomers] > tbody tr:last-child").clone(true);
            listarCustomer(lista);
        }
            });
 
            return false;
        });
 
       
     function UpdateCutomer(id){
          var customer;
          var row = $("[id*=tblCustomers] > tbody tr:last-child").clone(true);
            $("[id*=tblCustomers] tr").not(':has(th)').remove();
            customer =  lista[id];
            AppendRow(row, customer.customerId, customer.name, customer.country);
                row = $("[id*=tblCustomers] > tbody tr:last-child").clone(true);
         
     }  
      function DeleteCutomer(id){
          if (confirm("Do you want to delete this record?")) {
               var custom;
                custom =  lista[id];
                var customer={};
              customer['customerId'] = custom.customerId;
              customer['name'] = custom.name;
              customer['country'] = custom.country;
             var data = JSON.stringify(customer);
                $.ajax({
                    type: "POST",
                    url: host+"/llince-rest-spring-security/service/admin/deleteCustomer",
                    data: data,
                    crossDomain: true,
                    contentType: "application/json; charset=utf-8",
                    dataType: "json",
                    success:  function OnSuccess(response) {
                   var str = JSON.stringify(response);
                    lista = JSON.parse(str);
                  var customer;
          var row = $("[id*=tblCustomers] > tbody tr:last-child").clone(true);
            $("[id*=tblCustomers] tr").not(':has(th)').remove();
            customer =  lista[0];
            AppendRow(row, customer.customerId, customer.name, customer.country);
                row = $("[id*=tblCustomers] > tbody tr:last-child").clone(true);
            listarCustomer(lista);
        },error : function(e) {
				console.log("ERROR: ", e);
				alert("sin acceso");
			}
                });
            }
     }
    </script>
</div>
    </body>
</html>
