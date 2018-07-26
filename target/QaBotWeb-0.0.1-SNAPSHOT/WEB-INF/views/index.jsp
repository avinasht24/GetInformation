<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<title>QA BOT</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="<c:url value="/resources/css/style.css" />"  rel="stylesheet" type="text/css" />


<link href="<c:url value="/resources/css/layout.css" />" rel="stylesheet" type="text/css" />
<link href="<c:url value="/resources/css/img/coviamIcon.jpg" />" rel="icon" type="text/css" />


<script src="<c:url value="resources/css/maxheight.js" />" type="text/javascript"></script>
<!--[if lt IE 7]><link href="ie_style.css" rel="stylesheet" type="text/css" /><![endif]-->
</head>
<body id="page1" onload="new ElementMaxHeight();">
<!-- header -->
<div id="header">
  <div class="container">
    <div class="row-1">
      <div class="logo"><a href="#"><img alt="" src="<c:url value="/resources/css/img/coviamlogoqa.JPEG" />" height="100" width="400" /></a></div>
      
      <ul class="top-links">
        
      </ul>
    </div>
    <div class="row-2">
      <!-- nav box begin -->
      <div class="nav-box">
        <div class="left">
          <div class="right">
            <ul>
              <li><a href="<c:url value="/" /> " class="first"><em><b>HOME</b></em></a></li>
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

    <!-- main-banner-big begin -->
    <div class="main-banner-small" align ="left">
      <div class="inner" align="left">
        
        <h1>“Give them quality. That’s the best kind of advertising.” ~Milton Hersey</h1>
        </div>
        
    </div>
    <!-- main-banner-big end -->
    <div class="wrapper">
      <div class="col-1 maxheight">
        <!-- box begin -->
        <div class="box maxheight">
          <div class="border-top maxheight">
            <div class="border-right maxheight">
              <div class="border-bot maxheight">
                <div class="border-left maxheight">
                  <div class="left-top-corner maxheight">
                    <div class="right-top-corner maxheight">
                      <div class="right-bot-corner maxheight">
                        <div class="left-bot-corner maxheight" style="width: 963px; ">
                          <div class="inner">
                            <h2><b><font color="red">QA Help Center</font></b></h2>
                            <p>Please specify the input correctly in lowercases. </p>
                            <p>Follow sequence : Source(qa1/qa2/preprod/dev1/dev2) Type(api/center/db etc) Service(cart/promotion/etc)  </p>
                            
                            <p>Examples: </p>
                            <p>1. qa1 pnv emailid@mailinator.com  <br />
                            2. qa1 db order_id<br />
                          3. qa1 api cart<br />
                           4. qa1 center promotion<br />
                           5. qa2 product regular<br />
                           6. qa2 user pnv<br />
                           7. qa2 promotioncode<br />
                           
                           </p>
                            
                            <ul class="list1">
                              <li>
                              <form method="get" action="getInput"> 
                              <input name="inputValue" id="inputValue" size="50"></input>
                              <input value="SUBMIT"  type="submit" style="background-color: #f44336 ; color:whilte"></input>
                              </form>
                                                              <p></p>
                              
                                <p>Output : </p>
                                ${input}
                                <p></p>
                                
                               </div>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
        <!-- box end -->
     
        <!-- box end -->
      </div>
     
    </div>
  </div>
</div>
<!-- extra-content -->
<div id="extra-content">
  <div class="container">
    <div class="wrapper">
      <div class="col-1 maxheight">
        <!-- box begin -->
        <div class="box maxheight">
          <div class="border-top maxheight">
            <div class="border-right maxheight">
              <div class="border-bot maxheight">
                <div class="border-left maxheight">
                  <div class="left-top-corner maxheight">
                    <div class="right-top-corner maxheight">
                      <div class="right-bot-corner maxheight">
                        <div class="left-bot-corner maxheight">
                          <div class="inner">
                            <h2>About Us</h2>
                            <img class="img-indent" alt="" src="images/image_87x66.gif" /> We are a dynamic one stop technology company driven by the passion to disrupt technology today and define the world of tomorrow. We private label and create digital future tech platforms for you – using high end talent in an open culture, innovation driven environment. 
                            <div class="clear"></div>
                          </div>
                        </div>
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
      <div class="col-2 maxheight">
        <!-- box begin -->
        <div class="box maxheight">
          <div class="border-top maxheight">
            <div class="border-right maxheight">
              <div class="border-bot maxheight">
                <div class="border-left maxheight">
                  <div class="left-top-corner maxheight">
                    <div class="right-top-corner maxheight">
                      <div class="right-bot-corner maxheight">
                        <div class="left-bot-corner maxheight">
                          <div class="inner">
                            <h2>Testimonial</h2>
                            <img class="img-indent" alt="" src="images/image_87x66.gif" />
                            <h5>Testimonial Name</h5>
                            We know you love it - showing off how your customers love you and your company. Therefore there should be a place for it - which is here.
                            <div class="clear"></div>
                          </div>
                        </div>
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
    </div>
  </div>
</div>
<!-- footer -->
<div id="footer">
  <div class="container">
    <ul class="nav">
      <li><a href="<c:url value="/" /> ">Home</a>|</li>
      <li><a href="https://www.coviam.com">About Us</a>|</li>
      <li><a href="https://www.coviam.com">Solutions</a>|</li>
      <li><a href="https://www.coviam.com">Partners</a>|</li>
      <li><a href="https://www.coviam.com">Consulting</a>|</li>
      <li><a href="https://www.coviam.com">Contact Us</a></li>
    </ul>
    <div class="wrapper">
      <div class="fleft">Copyright &copy; 2018 <a href="#"> QA Team</a>. All Rights Reserved</div>
    </div>
  </div>
</div>
</body>
</html>
