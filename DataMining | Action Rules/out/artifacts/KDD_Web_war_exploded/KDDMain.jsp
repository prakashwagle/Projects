<!DOCTYPE html>
<html>
    <style>
        #header {
            background-color:black;
            color:white;
            text-align:center;
            padding:5px;
        }        
        /*button{height:20px; position:relative; margin: -20px -50px; width:100px; top:50%; left:35%;}*/
        #footer {
            background-color:black;
            color:white;
            clear:both;
            text-align:center;
            padding:5px;
            width:100%;
            bottom: 0px;
            position:absolute;
        }

    </style>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>KDD</title>
        <link rel="stylesheet" href="style/login.css">
        <link rel="stylesheet" href="style/style.css" type="text/css" />
        <link rel="stylesheet" href="style/reset.css">
    </head>

    <body> 
        <div id="header">
            <h1> Knowledge Discovery in Databases
            </h1>
        </div>
        <br><br>
        <div style="width:100%; margin:0 auto; padding: 20px; text-align: center; ">
            <h1 >  Action Rules </h1>
            <form action="Controller" method = "POST">

                <label> <h4> Please Enter Input File Path : </h4></label> <input type="text" title="name" style="width: 400px" name ="filename" />

                <br><h4> Initial Decision :
<!-- Alert, Warning, Stable, Sustainable -->
                    <select id="d1" name="d1">
                        <option value="All">All</option>
                        <option value="Alert">Alert</option>
                        <option value="Warning">Warning</option>
                        <option value="Stable">Stable</option>
                        <option value="Sustainable">Sustainable</option>
                    </select>
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    Desired Decision :
                    <select id="d2" name="d2">
                        <option value="Alert">Alert</option>
                        <option value="Warning">Warning</option>
                        <option value="Stable">Stable</option>
                        <option value="Sustainable">Sustainable</option>
                    </select>    </h4>
                <h2 ><button type="submit"  name="action" value="start" style="font-weight: bold; width: 140px">Start</button></h2>

                <textarea name="tempString" rows="40" cols="150" style="font-weight: bold">
                    ${tempString}
</textarea>

            </form>

        </div>

    </body>
</html>
