<%
    ui.includeJavascript("accessmonitor", "moment.min.js")
    ui.includeJavascript("accessmonitor", "analytics.js")
    ui.includeJavascript("accessmonitor", "Chart.js")
    ui.includeJavascript("accessmonitor", "utils.js")
    ui.includeJavascript("accessmonitor", "date_slider.js")
%>
<script type="text/javascript">

jq(function() { 

     jq('#tableList').dataTable({
            "aaSorting": [],
            "sPaginationType": "full_numbers",
            "bPaginate": true,
            "bAutoWidth": false,
            "bLengthChange": true,
            "bSort": true,
            "bJQueryUI": true
        });

    jq("#getUsers").click(function(){
       jq.getJSON('${ ui.actionLink("getDetailData") }',
           {
           start: "2018-05-10",
           end: "2018-05-10"
            })
       .error(function(xhr, status, err) {
            alert('AJAX error ' + err);
        })
        .success(function(users) {
            jq('#userTable').dataTable({
                "aaSorting": [],
                "sPaginationType": "full_numbers",
                "bPaginate": true,
                "bAutoWidth": false,
                "bLengthChange": true,
                "bSort": true,
                "bJQueryUI": true
            });
            var userTable = jq('#userTable').DataTable();
            for (index in users) {
                var user = users[index];
                userTable.fnAddData( [
                    user.userGiven + " " + user.userFamily,
                    user.actionType,
                    user.recordType,
                    user.recordId,
                    user.timestamp
                ]) 
            };
            document.getElementById("userList").style.display = "block";
            })
    })
});

</script>

<canvas id="myChart"></canvas>
<script>
function getDetailData(s, e){
    jq.getJSON('${ ui.actionLink("getDetailData") }',
        {
        start: s,
        end: e
         })
    .error(function(xhr, status, err) {
         alert('AJAX error ' + err);
     })
     .success(function(users) {
    //            alert(jq('#userTable').dataTable);
         jq('#userTable').dataTable({
             "bDestroy": true,
             "aaSorting": [],
             "sPaginationType": "full_numbers",
             "bPaginate": true,
             "bAutoWidth": false,
             "bLengthChange": true,
             "bSort": true,
             "bJQueryUI": true
         });
         var userTable = jq('#userTable').DataTable();
    //            alert(userTable.Rows.Count);

    //var dr = userTable.Rows[0];
    //
    //userTable.Rows.Clear();
    //userTable.Rows.Add(dr);

         userTable.fnClearTable();
         for (index in users) {
             var user = users[index];
             userTable.fnAddData( [
                 user.userGiven + " " + user.userFamily,
                 user.actionType,
                 user.recordType,
                 user.recordId,
                 new moment(user.timestamp, "DD.MMM.YYYY, hh:mm:ss").format("YYYY-MM-DD HH:mm:ss")
             ]) 
         };
         document.getElementById("userList").style.display = "block";
    })
}

function getByUserData(s, e){
    jq.getJSON('${ ui.actionLink("getByUserData") }',
        {
        start: s,
        end: e
         })
    .error(function(xhr, status, err) {
         alert('AJAX error ' + err);
     })
     .success(function(users) {
    //            alert(jq('#byUserTable').dataTable);
         jq('#byUserTable').dataTable({
             "bDestroy": true,
             "aaSorting": [],
             "sPaginationType": "full_numbers",
             "bPaginate": true,
             "bAutoWidth": false,
             "bLengthChange": true,
             "bSort": true,
             "bJQueryUI": true
         });
         var userTable = jq('#byUserTable').DataTable();
    //            alert(userTable.Rows.Count);

    //var dr = userTable.Rows[0];
    //
    //userTable.Rows.Clear();
    //userTable.Rows.Add(dr);

         userTable.fnClearTable();
         for (index in users) {
             var user = users[index];
             userTable.fnAddData( [
                 user.userGiven + " " + user.userFamily,
                 user.userId,
                 user.number,
                 new moment(user.start, "DD.MMM.YYYY, hh:mm:ss").format("YYYY-MM-DD HH:mm:ss") + " - " + new moment(user.end, "DD.MMM.YYYY, hh:mm:ss").format("YYYY-MM-DD HH:mm:ss")
             ]) 
         };
         document.getElementById("byUserList").style.display = "block";
    })
}

function getChartData(s, e){
    jq.getJSON('${ ui.actionLink("getChartData") }',
        {
        start: s,
        end: e
         })
    .error(function(xhr, status, err) {
         alert('AJAX error ' + err);
     })
     .success(function(users) {
     chart_data = [];
     maxY = 18;
//     chart_data.push({
//                x: new moment("22.Apr.2018", "DD.MMM.YYYY"),
//                y: 0
//              });
//             
         for (index in users) {
             var user = users[index]; 
             chart_data.push({
                x: new moment(user.start, "DD.MMM.YYYY, hh:mm:ss"),
                y: user.number
              })
              if (user.number > maxY ) {
                maxY = user.number;
              }
//              alert(user.start);
//             alert(user.start + ": " + user.number);
         };
//         chart_data.push({
//                x: new moment("31.May.2018", "DD.MMM.YYYY"),
//                y: 0
//              });
//         jq( "#slider-range" ).slider({
//                disabled: false
//              });
        
        chart.options.scales.yAxes = [{
            ticks: {
                suggestedMin: 0,
                suggestedMax: Math.ceil(maxY*1.1),
                stepSize: 1
            }
        }]
    
         chart.data.datasets[0].data = chart_data;
         chart.update();
//         alert(chart_data);
//         document.getElementById("userList").style.display = "block";
      }
    )
}
    
    
    
var data = [{
      x: new moment().add(-10, "d"),
      y: Math.random() * 100
    },
    {
      x: new moment().add(-8, "d"),
      y: Math.random() * 100
    },
    {
      x: new moment().add(-6, "d"),
      y: Math.random() * 100
    },
    {
      x: new moment().add(-4, "d"),
      y: Math.random() * 100
    },
    {
      x: new moment().add(-2, "d"),
      y: Math.random() * 100
    },
    {
      x: new moment().add(-0, "d"),
      y: Math.random() * 100
    },
  ];
var data1 = [{
      x: new moment().add(-11, "d"),
      y: Math.random() * 100
    },
    {
      x: new moment().add(-7, "d"),
      y: Math.random() * 100
    },
    {
      x: new moment().add(-4, "d"),
      y: Math.random() * 100
    },
    {
      x: new moment().add(-2, "d"),
      y: Math.random() * 100
    },
    {
      x: new moment().add(-0, "d"),
      y: Math.random() * 100
    },
    {
      x: new moment().add(1, "d"),
      y: Math.random() * 100
    },
  ];    
var data2 = [{
      x: new moment().add(-10, "m"),
      y: Math.random() * 100
    },
    {
      x: new moment().add(-8, "m"),
      y: Math.random() * 100
    },
    {
      x: new moment().add(-6, "m"),
      y: Math.random() * 100
    },
    {
      x: new moment().add(-4, "m"),
      y: Math.random() * 100
    },
    {
      x: new moment().add(-2, "m"),
      y: Math.random() * 100
    },
    {
      x: new moment().add(-0, "m"),
      y: Math.random() * 100
    },
  ];  
  
  
 var chart_data = [];
 
 
function sliderDo(s,e) {

    getChartData(s, e);
    getDetailData(s, e);
    getByUserData(s, e);
    
    if(s==e){
//        chart.data.datasets[0].data = data2;
        chart.options.scales.xAxes = [{
            type: 'time',
            time: {
                unit: 'hour',
            }
          }]
        
    } else {
//        if(chart.data.datasets[0].data == data) {
//            chart.data.datasets[0].data = data1;
//        }else {
//            chart.data.datasets[0].data = data;
//        }
        
        chart.options.scales.xAxes = [{
            type: 'time',
            time: {
                unit: 'day',
            }
          }]
    }
}

//function newDate(days) {
//    if(oneDay){
//        return moment().add(days, 'm');
//    } else {
//        return moment().add(days, 'd');
//    }
//}

var config = {
  type: 'bar',
  data: {
      datasets: [{
        data: data,
        label: "Number of Access",
//        borderColor: "#3e95cd",
//        fill: false
      }]
    },
//  data: {
//    labels: [newDate(-4), newDate(-3), newDate(2), newDate(3), newDate(4), newDate(5), newDate(6)],
//    datasets: [{
//      label: "My First dataset",
//      data: [1, 3, 4, 2, 5, 4, 2],
//    }]
//  },
  options: {
    scales: {
      yAxes: [{
            ticks: {
                suggestedMin: 0,
                suggestedMax: 100
            }
        }],
      xAxes: [{
        type: 'time',
        distribution: 'linear',
        time: {
            unit: 'day',
        }
      }],
    },
  }
};

var ctx = document.getElementById("myChart").getContext("2d");
var chart = new Chart(ctx, config);
    
        
</script>
    
<div>
    <br>
<div style="border: 0; width: 1000px">
  <label for="amount">Date range:</label> <input type="text" id="amount" style="border: 0; color: #000000; font-weight: bold; display: inline;white-space:nowrap;" size="50"/>
</div>
 
<div id="slider-range"></div>

<!--<div>
    <button id="getUsers" type="button">By User in given timeframe</button> <button id="getUsers" type="button">Detail</button>
</div>-->
    



<div class="Table" id="byUserList"  style="margin-top: 20px" hidden>
<table id="byUserTable"  border="1" class="display" cellspacing="0" width="50%">
<thead>
  <tr>
        <th>User Name</th>
        <th>User Id</th>
        <th>Number of Access</th>
        <th>Time Frame</th>
  </tr>
</thead>
<tbody >
</tbody>
</table>

</div>


<div class="Table" id="userList" style="margin-top: 20px" hidden>
<table id="userTable"  border="1" class="display" cellspacing="0" width="50%">
<thead>
  <tr>
        <th>User Name</th>
        <th>Action Type</th>
        <th>Record Type</th>
        <th>Record Id</th>
        <th>Timestamp</th>
  </tr>
</thead>
<tbody >
</tbody>
</table>

</div>


<!--<button id="getUsers" type="button">Detail</button>-->
</div>