<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>


<head>
<link href="<c:url value="/css/style.css" />" rel="stylesheet">
    <meta property="al:mobile:url" content=${appUrl}; />
    <meta property="al:mobile:app_name" content="Anitales" />


       <meta property="og:title" content="${user.userName}'s story '${book.title}'" />
       <meta property="og:type" content="website" />
       <meta property="al:web:should_fallback" content="false" />
    <title>${user.userName}'s story '${book.title}' </title>
       <link href="<c:url value="/css/style.css" />" rel="stylesheet">
        <script type='text/javascript'>
              function gotoApp() {
                            var link = ${appUrl};
                            setTimeout(function () { window.location.href = ${appUrl}; }, 2500);
                            window.location.href = link;
                            console.log(window.location.href);
                  }
        </script>
   </head>
<h1>${book.title}</h1>
   <body>
    <div id="bookCovers" onclick="gotoApp()" >
       <c:forEach items="${bookCovers}" var="bookCover">
               <img src="${bookCover}" class="storyThumb"/>
       </c:forEach>
   </div>
  <div id="title" onclick="gotoApp()" class="storyTitle">
        ${user.userName} created a story '${book.title}' in Anitales
        <br/>
  </div>
   <div id="summary" onclick="gotoApp()" class="storySummary">
          ${book.summary}
   </div>
   </body>



</html>