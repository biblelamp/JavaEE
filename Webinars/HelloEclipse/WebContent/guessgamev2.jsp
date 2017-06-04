<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Guess Number Game v2.0</title>
    <%
    	int guess = -1, number, counter;
		String message = "You have to guess number from 1 up 10 using 3 attempts.";
    	String answer_str = request.getParameter("answer_str");
		String guess_str = request.getParameter("guess");
    	String number_str = request.getParameter("number");
    	String counter_str = request.getParameter("counter");
    	String backPath = request.getContextPath(); // path to the container
    	String selfPath = backPath + request.getServletPath(); // add name of jsp file
    %>
</head>
<body>
	<h2>Guess Number Game v2.0</h2>
	<%
		if (guess_str == null) { // to start game
			number = (int) Math.floor(Math.random()*10 + 1);
			answer_str = "1111111111";
			counter = 3;
		} else {
			guess = Integer.parseInt(guess_str);
			char[] chars = answer_str.toCharArray();
			chars[guess -1] = '0';
			answer_str = new String(chars);
			number = Integer.parseInt(number_str);
			counter = Integer.parseInt(counter_str);
			counter--;
			message = (guess == number)?
					"Congrats! You guessed! You can game again." :
					"My number is " + ((guess < number)? "greater." : "less.") +
					((counter == 0)? 
							"Sorry, You lost. You can try again." : "");
			if (counter == 0 || guess == number) {
				number = (int) Math.floor(Math.random()*10 + 1);
				answer_str = "1111111111";
				counter = 3;
			}
		}
	%>
	<table>
		<tr>
			<%
				for (int i = 1; i <= 10; i++) {
			%>
			<td align="center">
				<form method="POST" action="<%= selfPath %>">
					<h1><%= i %></h1>
					<input type="hidden" name="answer_str" value="<%= answer_str %>"/>
					<input type="hidden" name="counter" value="<%= counter %>"/>
					<input type="hidden" name="number" value="<%= number %>"/>
					<input type="hidden" name="guess" value="<%= i %>"/>
					<input type="submit"
					<%
						if (answer_str.charAt(i - 1) == '0') {
					%>
						disabled="disabled"
					<%
						}
					%>
					value="OK"/> <!-- disabled="disabled" -->
				</form>		
			</td>
			<%
				}
			%>
		</tr>
	</table>
	<p style="color:lightgray">Control: [ <%= counter %> : <%= number %> : <%= guess %> ]</p>
	<h3 style="color:red"><%= message %></h3>
	<p>Go <a href="<%= backPath %>">back</a> | <a href="<%= selfPath %>">Reset</a> game</p>
</body>
</html>