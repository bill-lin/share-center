<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>


<head>
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

   <body>
    <div id="container" onclick="gotoApp()" class="background">
       <c:forEach items="${bookCovers}" var="bookCover">
               <img src="${bookCover}" />
       </c:forEach>
   </div>
   <div id="container" onclick="gotoApp()" class="storySummary">
          ${book.summary}
   </div>
   </body>



</html>