function courseDetails() {

	var selectedCourse=$( "#selectCourse" ).val();
    //alert(selectedCourse);
      
  }
  
  function preCourse()
  {
  	var selectedCourse=$( "#selectCourse" ).val();
  //	alert(selectedCourse);
  	var contextRoot = "/" + window.location.pathname.split('/')[1];
  	$.ajax({
		             type: "GET",
		             contentType: "application/json",
		             url: "http://localhost:8080/predcourse?courseNum="+selectedCourse,
		             dataType: 'json',
		             success: function (data) { 
		                console.log(data);
		               $("tbody").html("");
		               removeOptions(document.getElementById("selectPreCourse"));
		                $("#selectPreCourse").append('<option value="None" > Select </option>');
		               // $("#selectPreCourse").text(data[0].precourse);
		                //alert(data[0].course_number.name);
		               // alert(data[0].precourse);
		                
		                
		                // Get select
   					 var select = document.getElementById('selectPreCourse');

   					 // Add options
   					 for (var i in data) 
   					 {
     				 	//console.log(data[i].precourse);
     				 	
        				$("#selectPreCourse").append('<option value=' + data[i].precourse + '>' + data[i].precourse + '</option>');
    				 }

    				// Set selected value
    				//$(select).val(data[0].precourse);
		              
		             },
		             error: function (e) {
		             console.log("hi");
		                 removeOptions(document.getElementById("selectPreCourse"));
		             }
			});
  }
  
  function removeOptions(selectbox)
{
    var i;
    for(i = selectbox.options.length - 1 ; i >= 0 ; i--)
    {
        selectbox.remove(i);
    }
}
function register()
{
	alert("save pls");
}
function showBlock()
  {
  	//alert("show blocks");
  	var selectedCourse=$( "#selectCourse" ).val();
  //	alert(selectedCourse);
  	var contextRoot = "/" + window.location.pathname.split('/')[1];
  	$.ajax({
		             type: "GET",
		             contentType: "application/json",
		             url: "http://localhost:8080/showblock?courseNum="+selectedCourse,
		             dataType: 'json',
		             success: function (data) { 
		                console.log(data);
		             	
		                let tableData = '';
		                for(row of data){
		                	tableData += `
		                		<tr>
		   							<td><input class="form-check-input" type="checkbox" path="${row.courseNum}"></td>
		                			<td>${row.block}</td>
		                			<td>${row.professor.name}</td>
		                			<td>${row.capacity}</td>
		                			<td>${row.enrolled}</td>
		                			<td>${row.seats}</td>
		                			
		                		</tr>
		                	`;
		                }
		                $("#blockDetails").html(tableData);
		             
		                
		                
		                // Get select
   					 var tdCourse = document.getElementById('courseNum');

   					
		              
		             },
		             error: function (e) {
		             console.log("hi");
		                 removeOptions(document.getElementById("selectPreCourse"));
		             }
			});
  }