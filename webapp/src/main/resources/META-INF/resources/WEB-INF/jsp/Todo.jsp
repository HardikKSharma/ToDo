<%@ include file="common/header.jspf"%>
<body>
<%@ include file="common/navigation.jspf"%>
   <div class="container">
       <h1>Enter Todo Details</h1>
       <form:form method="post" modelAttribute="todo">
       <fieldset class="mb-3">
         <form:label path="description">Description</form:label>
        <form:input type="text" path="description" required="required"/>
           <form:errors path="description" cssClass="text-warning"/>
           </fieldset>
           
           <fieldset class="mb-3">
         <form:label path="date">Target Date</form:label>
        <form:input type="text" path="date" required="required"/>
           <form:errors path="date" cssClass="text-warning"/>
           </fieldset>
           <form:input type="hidden" path="id" required="required"/>
           <form:input type="hidden" path="done" required="required"/>
           <input type="submit" class="btn btn-success"/>
       </form:form>
   </div>
    <script type="text/javascript">
    $('#date').datepicker({
    format: 'mm/dd/yyyy',
    startDate: '-3d'
});</script>
  <%@ include file="common/footer.jspf"%>