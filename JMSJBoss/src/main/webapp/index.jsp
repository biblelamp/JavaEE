<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
                      "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JMS example</title>
        <script src="https://code.jquery.com/jquery-3.2.1.min.js" type="text/javascript"></script>
        <script>
           function sendMessages() {
     	       	$.ajax({
        	   		url : 'service',
        	   		data : {
    					mode : 'send',
    					prefix: document.getElementById('prefix').value
        			},
           			success : function(response) {
           				$('#sendJMS').html(response);
           			}
           		});
           }
           function listMessages() {
               $.ajax({
                   url : 'service',
                   data : {
                       mode : 'list' },
                   success : function(response) {
                       $('#listJMS').html(response);
                   }
               });
           }
           function receiveMessages() {
               $.ajax({
                   url: 'service',
                   data: {
                       mode: 'receive'
                   },
                   success: function (response) {
                       $('#receiveJMS').html(response);
                   }
               });
           }
           function resendMessages() {
                $.ajax({
                   url : 'service',
                   data : {
                       mode : 'resend' },
                   success : function(response) {
                       $('#resendJMS').html(response);
                   }
               });
          }
        </script>
  </head>
  <body>
      <h2>Example JMS using JBoss</h2>
      <p>Send messages JBoss with prefix <input id="prefix" type="text" size="5"></p>
         <input type="submit" width="80" value="Send" onClick="sendMessages()">
         <span id="sendJMS" style="color: #3f48cc;">&nbsp;</span>

      <p>List messages from DLQueue</p>
        <input type="submit" width="80" value="List" onClick="listMessages()">
        <span id="listJMS" style="color: #7349a4;">&nbsp;</span>

      <p>Check received messages from ExpiryQueue</p>
         <input type="submit" width="80" value="Check" onClick="receiveMessages()">
         <span id="receiveJMS" style="color: #7349a4;">&nbsp;</span>

      <p>Resend messages from DLQueue</p>
        <input type="submit" width="80" value="Resend" onClick="resendMessages()">
        <span id="resendJMS" style="color: #7349a4;">&nbsp;</span>
  </body>
</html>
