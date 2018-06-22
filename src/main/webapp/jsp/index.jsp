<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>~*~*~*~ Vending Machine ~*~*~*~</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/home.css" rel="stylesheet">
    </head>
    <body>

        <h1 align="center">~*~*~*~VENDING MACHINE~*~*~*~</h1>

        <div id="outside-box" class="container">

            <div class="col-md-7" id ="snackDisplay">
                <div class="row">
                    <c:forEach var="snacks" items="${items}">
                        <div class="col-md-4">
                            <form action="selectItem/${snacks.snackId}" method = "POST">
                                <button type ="submit">
                                    <div class ="itemStyle">
                                        <p>${snacks.snackId}</p>
                                        <p> ${snacks.snackName}</p>
                                        <p>${snacks.snackCost}</p>
                                        <p>${snacks.quantitySnacks}</p>
                                    </div>
                                </button>
                            </form>
                        </div>
                    </c:forEach>
                </div>
            </div>

            <!-- 2nd column Menu items -->
            <div id="displayDiv" class="col-md-3">
                <!-- Money Box -->
                <div id="moneyDiv" class="col-md-6 box-other">
                    <h5 align="center"> ~*~ Total $ In ~*~</h5>
                    <input id="moneyDisplay" class="form-control" type="text" value="${userMoney}" readonly >

                    <div class="col-md-6">
                        <form action="inputMoney" method="POST">
                            <button id="dollar" type="submit" class="btn btn-default"> Add Dollar
                                <input type="hidden" name="userMoney" value="1.00"/></button>
                        </form>
                        <form action="inputMoney" method="POST">
                            <button id="quarter" type="submit" class="btn btn-default"> Add Quarter
                                <input type="hidden" name="userMoney" value=".25"/></button>
                        </form>
                    </div>

                    <div class="col-md-6">
                         <form action="inputMoney" method="POST">
                            <button id="dime" type="submit" class="btn btn-default"> Add Dime
                                <input type="hidden" name="userMoney" value=".10"/></button>
                        </form>
                        <form action="inputMoney" method="POST">
                            <button id="nickel" type="submit" class="btn btn-default"> Add Nickel
                                <input type="hidden" name="userMoney" value=".05"/></button>
                        </form>
                    </div>

                </div>


                <!-- Messages box -->

                <div id="messagesDiv" class="col-md-12 box-other">
                    <h5 align="center"> ~*~ Messages ~*~ </h5>
                    <input type="text" class="form-control" readonly value="${messages}"/>

                    <label for="itemDisplay" class="control-label" style="display: inline-block; max-width: 30%"></label>

                    <div>
                        <input type="text" style="max-width: 90%" value = "${selection}" readonly/>
                    </div>

                    <form method="POST" action="makePurchase"/>
                    <button type="submit" class="btn btn-default" style="width: 60%">Make Purchase</button>
                    </form>

                </div>

                <!-- Change -->
                <div id="changeDiv" class="col-md-12 box-other mr-auto p-2">
                    <h5 align="center">~*~ Change ~*~</h5>
                    <form method="POST" action="returnChange"/>
                    <button type="submit" class="btn btn-default" style="width: 60%">Return Change</button>
                    <textarea id="changeDisplay" type="textarea" class="form-control" rows="6" placeholder="${change}" readonly></textarea>
                    </form>
                </div>
            </div>

        </div> 
        <script src="js/jquery-3.1.1.min.js"></script>
        <script src="js/bootstrap.js"></script>

    </body>

</html>

