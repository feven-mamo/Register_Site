function courseDetails() {

	var selectedCourse=$( "#selectCourse" ).val();
    // alert(selectedCourse);
      
  }

  function preCourse()
  {
  	var selectedCourse=$( "#selectCourse" ).val();
  // alert(selectedCourse);
  	var contextRoot = "/" + window.location.pathname.split('/')[1];
  	$.ajax({
		             type: "GET",
		             contentType: "application/json",
		             url: "http://localhost:8080/predcourse?courseNum="+selectedCourse,
		             dataType: 'json',
		             success: function (data) { 
		                //console.log(data);
		               $("tbody").html("");
		               removeOptions(document.getElementById("selectPreCourse"));
		                $("#selectPreCourse").append('<option value="None" > Select </option>');
		               // $("#selectPreCourse").text(data[0].precourse);
		                // alert(data[0].course_number.name);
		               // alert(data[0].precourse);
		                
		                
		                // Get select
   					 var select = document.getElementById('selectPreCourse');

   					 // Add options
   					 for (var i in data) 
   					 {
        				$("#selectPreCourse").append('<option value=' + data[i].precourse + '>' + data[i].precourse + '</option>');
    				 }

		             },
		             error: function (e) {
		             //console.log("hi");
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
	let blockId = $('input[name=blockid]:checked').val();
	var selectedCourse=$( "#selectCourse" ).val();
	alert("blockDetail - "+blockId);

	
	$.ajax({
        type: "POST",
        contentType: "application/json",
        url: "http://localhost:8080/savePreference?courseNum="+selectedCourse,
        dataType: 'json',
        
        data: blockId ,
        success: function (result) { 
          alert("success!");
        },
        error: function (e) {
        console.log("hi");
            removeOptions(document.getElementById("selectPreCourse"));
        }
});
}

function showBlock()
  {
  	// alert("show blocks");
  	var selectedCourse=$( "#selectCourse" ).val();
  // alert(selectedCourse);
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
		   							<td><input class="form-check-input blockcourse" type="radio" name="blockid" value="${row.id}"></td>
		                			<td>${row.block}</td>
		                			<td>${row.professor.name}</td>
		                			<td>${row.capacity}</td>
		                			<td>${row.enrolled}</td>
		                			<td>${row.seats}</td>		                			
		                		</tr>
		                	`;
		                }
		                $("#blockDetails").html(tableData);
		             
   					 var tdCourse = document.getElementById('courseNum');
 
		             },
		             error: function (e) {
		            // console.log("hi");
		                 removeOptions(document.getElementById("selectPreCourse"));
		             }
			});
  }