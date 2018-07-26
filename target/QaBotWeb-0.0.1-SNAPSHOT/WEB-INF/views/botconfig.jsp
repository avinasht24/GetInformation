<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<script type="text/javascript">
function submitMessage(){
alert("Add Configuration request has been submitted.");
} 
function validateTextBox()
{
    if (document.getElementById("source_type").value === "PROMOTIONCODE") {
        document.getElementById("bot_keyword").value="PROMOTIONCODE";
          document.getElementById("bot_keyword").disabled=true;
 
    } else {
        document.getElementById("bot_keyword").disabled=false;
      document.getElementById("bot_keyword").value="";

    }
}
</script>
<head>
<title>QA BOT</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="<c:url value="/resources/css/style.css" />"  rel="stylesheet" type="text/css" />
<link href="<c:url value="/resources/css/layout.css" />" rel="stylesheet" type="text/css" />
<link href="<c:url value="/resources/css/img/coviamIcon.jpg" />" rel="icon"  />

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
        <h1>“Quality means doing it right even when no one is looking.”— Henry Ford</h1>
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
      <div class="col-1 maxheight">
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
                          <form method="post" action="botconfiginsert" >
                            <h2 ><b><font color="red">BOT Configuration</font></b></h2>
                            <p>Add the configuration required for api/center<br />
                             <br/>Example: For xPromotion in uat b <br />
                           <br/> <b>Select Environment</b> : QA2<br />
                             <b>Select Type</b> : API/Center<br />                          
                            <b>Enter Bot Keyword</b> (same would be used as: promotion api uatb) : promotion
                            Note: If service has two words, then give it as one. ex: conditionalcart
                            <br /><b>Enter API URL</b> : http://172.20.32.130:8080/x-promotion/docs/</p>
                            <ul class="list3 color1">
                              <li><span>Select Environment</span>
                                 <select name="environment">
                              <option  value="QA-1">QA-1</option>
                              <option value="QA-2">QA-2</option>
                              <option  value="DEV-1">DEV-1</option>
                              <option  value="DEV-2">DEV-2</option>
                               <option  value="PRE-PROD">PRE-PROD</option>
                              
                              </select>
                              </li>
                           <li><span>Select Type</span>
                                 <select id="source_type" name="source_type" onChange="validateTextBox();">
                              <option value="API">API</option>
                              <option value="CENTER">CENTER</option>
                              <option value="PRODUCT">PRODUCT</option>
                              <option value="USER">USER</option>
                              <option value="PROMOTIONCODE">PROMOTIONCODE</option>
                              </select>
                              </li>
                              <li><span>Enter the keyword </span>
                              <input type="text" name="bot_keyword" id="bot_keyword"></input>
                              </li>
                               <li><span>Enter the url/info </span>
                              <input type="text" name="url" id="url"></input>
                              </li>
                              
                            </ul>
                            <p>Confirm above details are correct before submitting.</p>
                            <p class="aligncenter"><img alt="" src="images/price1.jpg" /></p>
                            <button type="submit" onclick="submitMessage();" class="buttonconfig" > Add Now</button> </div>
                            <p>
                             &nbsp;&nbsp; &nbsp;&nbsp;   <b>${configDetails.insertFlag} </b> 
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
      <div class="col-2 maxheight">
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
                         <form method="post" action="botconfigresults" >
                          <div class="inner">
                            <h2><b><font color="red">View BOT Configuration</font></b></h2>
                            <button type="submit"  class="buttonconfigview"  margin-left: 121px; margin-top: 1px;> View Now</button>
                            <br/></br><p>The configurations added are displayed here</p>
                           
                            <table border="1">
        <tr style="font-size: 13"  bgcolor="#A52A2A">
            <td><b><font color="white">Environment</b></td>
            <td><b><font color="white">Source Type</b></td>
            <td><b><font color="white">Keyword</b></td>
            <td><b><font color="white">URL</b></td>
        </tr>
        <c:forEach var="qaBotConfig" items="${qaBotConfigs}">
            <tr style="font-size: 10"  bgcolor="#DEB887">
                <td>${qaBotConfig.getEnvironment()}</td>
                <td>${qaBotConfig.getSource_type()}</td>
                <td>${qaBotConfig.getBot_keyword()}</td>
                <td>${qaBotConfig.getUrl()}</td>
            </tr>
            </c:forEach>
    </table>
                            
                            
                             </form> </div>
                           
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
