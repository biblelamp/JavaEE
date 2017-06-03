<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Guess Number Game</title>
    <%
    	int guess = -1, number, counter;
    	String message = "You have to guess number<br/>from 1 up 10 using 3 attempts.";
    	String guess_str = request.getParameter("guess");
    	String number_str = request.getParameter("number");
    	String counter_str = request.getParameter("counter");
    	String backPath = request.getContextPath(); // path to the container
    	String selfPath = backPath + request.getServletPath(); // add name of jsp file
    %>
</head>
<body>
	<h2>Guess Number Game</h2>
	<%
		if (guess_str == null || guess_str.length() == 0) { // to start game
			number = (int) Math.floor(Math.random()*10 + 1);
			counter = 3;
		} else {
			// next attempt
			guess = Integer.parseInt(guess_str);
			number = Integer.parseInt(number_str);
			counter = Integer.parseInt(counter_str);
			counter--;
			if (guess == number) {
				message = "Congrats! You guessed, it was " + number + "! You can game again.";
			} else {
				message = "Conceived number is " + ((guess < number)? "greater." : "less.");
				if (counter == 0)
					message += "<br/>Sorry, You lost. You can try again.";
			}
			if (counter == 0 || guess == number) { // reset number && counter for game
				number = (int) Math.floor(Math.random()*10 + 1);
				counter = 3;
			}
		}
	%>
	<h3 style="color:red"><%= message %></h3>
	<form method="POST" action="<%= selfPath %>">
		<input type="hidden" name="counter" value="<%= counter %>"/>
		<input type="hidden" name="number" value="<%= number %>"/>
		<input type="text" name="guess"/>
		<input type="submit" value="Send"/>
	</form>
	<p style="color:lightgray">Control: [ <%= counter %> : <%= number %> : <%= guess %> ]</p>
	<p>Go <a href="<%= backPath %>">back</a> | <a href="<%= selfPath %>">Reset</a> game
</body>
</html>