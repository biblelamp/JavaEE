<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Guess Number Game</title>
    <%
    	int guess = -1, number, counter;
    	double number_double = 0;
    	String message = "You have to guess number from 1 up 10.";
    	String guess_str = request.getParameter("guess");
    	String number_str = request.getParameter("number");
    	String counter_str = request.getParameter("counter");
    %>
</head>
<body>
	<h2>Guess Number Game</h2>
	<%
		if (guess_str == null || guess_str.length() == 0) { // to start game
			number_double = Math.random()*10 + 1;
			number = (int) Math.floor(number_double);
			counter = 0;
		} else {
			guess = Integer.parseInt(guess_str);
			number = Integer.parseInt(number_str);
			counter = Integer.parseInt(counter_str);
			counter++;
			message = (guess == number)? "Congrats! You guessed!" :
				"Your number is " + ((guess < number)? "greater." : "less.");
		}
	%>
	<p><%= counter %> : <%= number %> : <%= guess %></p>
	<p style="color:red"><%= message %></p>
	<form method="POST" action="guessnmgame.jsp">
		<input type="hidden" name="counter" value="<%= counter %>"/>
		<input type="hidden" name="number" value="<%= number %>"/>
		<input type="text" name="guess"/>
		<input type="submit" value="Submit"/>
	</form>
</body>
</html>