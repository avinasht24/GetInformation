<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<script  type="text/javascript">
function submitMessage(){
alert("Bug Creation request has been submitted.");
}
</script>
<head>
<title>QA BOT</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="<c:url value="/resources/css/style.css" />"  rel="stylesheet" type="text/css" />
<link href="<c:url value="/resources/css/layout.css" />" rel="stylesheet" type="text/css" />
<link href="<c:url value="/resources/css/img/coviamIcon.jpg" />" rel="icon" />

<script src="<c:url value="resources/css/maxheight.js" />" type="text/javascript"></script>
<!--[if lt IE 7]><link href="ie_style.css" rel="stylesheet" type="text/css" /><![endif]-->
</head>
<body id="page3" onload="new ElementMaxHeight();">
<!-- header -->
<div id="header">
  <div class="container">
    <div class="row-1">
      <div class="logo"><a href="#"><img alt="" src="<c:url value="/resources/css/img/coviamlogoqa.JPEG" />" height="100" width="400" /></a></div>
      
      <ul class="top-links">
        <li><a href="#"><img alt="" src="images/top-icon1.jpg" /></a></li>
        <li><a href="#"><img alt="" src="images/top-icon2.jpg" /></a></li>
        <li><a href="#"><img alt="" src="images/top-icon3.jpg" /></a></li>
      </ul>
    </div>
    <div class="row-2">
      <!-- nav box begin -->
      <div class="nav-box">
        <div class="left">
          <div class="right">
            <ul>
     		<li><a href="<c:url value="/" /> "><em><b>HOME</b></em></a></li>
              <li><a href="botconfig"><em><b>QA BOT CONFIG</b></em></a></li>
                            <li><a href="bugcreation"><em><b>JIRA BUG</b></em></a></li>
              <li><a href="https://www.coviam.com"><em><b>COVIAM</b></em></a></li>
              <li><a href="http://www.google.com"><em><b>LINKS</b></em></a></li>
              <li><a href="http://www.google.com" class="last"><em><b>CONTACT US</b></em></a></li>
            </ul>
          </div>
        </div>
      </div>
      <!-- nav box end -->
    </div>
  </div>
</div>
<!-- content -->
<div id="content">
  <div class="container">
    <!-- main-banner-small begin -->
    <div class="main-banner-small">
      <div class="inner">
        <h1>“Testing leads to failure, and failure leads to understanding.”— Burt Rutan</h1>
      </div>
    </div>
    <!-- main-banner-small end -->
    <div class="section">
      <!-- box begin -->
      <div class="box">
        <div class="border-top">
          <div class="border-right">
            <div class="border-bot">
              <div class="border-left">
                <div class="left-top-corner">
                  <div class="right-top-corner">
                    <div class="right-bot-corner">
                      <div class="left-bot-corner">
                       
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
      <!-- box end -->
    </div>
    <div class="wrapper">
      <div class="col-1 maxheight" style="width: 930px;">
        <!-- box1 begin -->
        <div class="box1 maxheight">
          <div class="border-top maxheight">
            <div class="border-right maxheight">
              <div class="border-bot maxheight">
                <div class="border-left maxheight">
                  <div class="left-top-corner maxheight">
                    <div class="right-top-corner maxheight">
                      <div class="right-bot-corner maxheight">
                        <div class="left-bot-corner maxheight">
                          <div class="inner">
                          <form method="post" action="newbugcreation" >
                            <h2 ><b><font color="red">JIRA BUG CREATION</font></b></h2>
                            <p><b>Now, creating a bug is made simple !</b><br />
                            <p> 
                            1. Give JIRA username  without '@gdn-commerce.com'<br />
                            2. Give correct assignee name  without '@gdn-commerce.com'<br />
                            3. Give correct Project codes. ex: x-shipping is XSHIP<br />
                            <br />
                            
                            
                              <li><span>Enter the JIRA Username : </span>&nbsp; 
                              <input type="text" name="jira_username" id="jira_username" style="margin-left:12em;"></input>
                              </li>
                              <li><span>Enter the JIRA Password : </span>
                              <input type="password" name="jira_password" id="jira_password" style="margin-left:155px;"></input>
                              </li>
                                <li><span>Enter the Assignee </span>
                              <input type="text" id="assignee_name" name="assignee_name" style="margin-left:194px;"></input>
                              </li>
                              <li><span>Enter the Project Code (example: Promotion is XPROM) </span>
                              <input type="text" name="project_code" id="project_code" style="margin-left:0em;"></input>
                              </li>
                              
                               <li><span>Enter the Title Summary </span>
                              <input type="text" name="title_summary" id="title_summary" style="margin-left:164px;"></input>
                              </li>
                               <li><span>Enter the Description </span></br>
                              <textarea rows="4" cols="80" id="bug_description" name="bug_description" ></textarea>
                              </li>
                            
                              
                            </ul>
                            <p>Confirm above details are correct before submitting.</p>
                            <p class="aligncenter"><img alt="" src="images/price1.jpg" /></p>
                            <button type="submit" onclick="submitMessage();" class="buttonconfig" > CREATE BUG</button> </div>
                            <p>
                             &nbsp;&nbsp; &nbsp;&nbsp;   <b>${bugDetails.insertFlag} </b> 
                            </p>
                            </form>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
        <!-- box1 end -->
      </div>

        <!-- box1 end -->
    
        <!-- box1 end -->
      </div>
    </div>
  </div>
</div>
<!-- footer -->
<div id="footer">
  <div class="container">
    <ul class="nav">
      <li><a href="#">Home</a>|</li>
      <li><a href="#">About Us</a>|</li>
      <li><a href="#">Solutions</a>|</li>
      <li><a href="#">Partners</a>|</li>
      <li><a href="#">Consulting</a>|</li>
      <li><a href="#">Contact Us</a></li>
    </ul>
    <div class="wrapper">
      <div class="fleft">Copyright &copy; 2018 <a href="#"> QA TEAM</a>. All Rights Reserved</div>
    </div>
  </div>
</div>
</body>
</html>
