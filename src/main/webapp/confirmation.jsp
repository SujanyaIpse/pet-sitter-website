<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Confirmation Page</title>
    <style>
        .c-cont{
            background-color: #9b88b8;
            margin-top: 50px;
            margin-left: 50px;
            margin-right: 50px;
            padding: 40px;
            border-radius: 30px;
            color: white;
            font-size: 40px;
        }

        p{
            position: absolute;
            right: 2%;
            font-weight: bold;
        }
        
        button{
        	background-color: "#E2D8F1"
        	padding: 20px;
        	border: none;
        	border-radius: 20px;
        	margin-top: 20px;
            margin-left: 50px;
        }
    </style>
</head>
<body>
    <div class = "c-cont">
        <h3> You have successfully booked an appointment!! You will receive a confirmation call soon. </h3>
    </div>
    
    <button id = "viewHomePage"> View Home Page </button>

    <p> For any queries - akshayspdange@gmail.com </p>
    
    <script>  
    	let viewHomePageEl = document.getElementById("viewHomePage");

    	viewHomePageEl.onclick = function() {
          	window.location.assign("mainPage.html")
              };
    </script>
</body>

</html>

