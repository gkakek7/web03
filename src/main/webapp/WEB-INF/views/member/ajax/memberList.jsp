<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<table class="table table-bordered text-center">
   <thead>
   <tr>
      <th>일련번호</th>
      <th>회원명</th>
      <th>이메일</th>
      <th>휴대폰번호</th>
      <th>주소1</th>
      <th>마일리지</th>	
   </tr> 
   </thead>
   <tbody id="ttbody">
<%--    <c:choose> --%>
<%--       <c:when test="${not empty memberList }"> --%>
<%--          <c:forEach items="${memberList }" var="member"> --%>
<!--          <tr> -->
<%--             <td>${member.rnum }</td> --%>
<%--             <td>${member.memName }</td> --%>
<%--             <td>${member.memMail }</td> --%>
<%--             <td>${member.memHp }</td> --%>
<%--             <td>${member.memAdd1 }</td> --%>
<%--             <td>${member.memMileage }</td> --%>
<!--          </tr> -->
<%--          </c:forEach> --%>
<%--       </c:when> --%>
<%--       <c:otherwise> --%>
<!--          <tr> -->
<!--             <td colspan="6">조건에 맞는 회원이 없음</td> -->
<!--          </tr> -->
<%--       </c:otherwise> --%>
<%--    </c:choose> --%>
   </tbody>
   <tfoot>
      <tr>
         <td colspan="6">
            <form id="submitForm" method="get" class="border border-danger m-3 p-3">
               <h6>전송UI(hidden Form)</h6>
               <input type="text" name="page" placeholder="page"/>
               <input name="simpleCondition.searchType" placeholder="searchType"/>
               <input name="simpleCondition.searchWord" placeholder="searchWord"/>
            </form>
            <div data-pg-role="searchUI" data-pg-target="#submitForm"
                  class="m-3 p-3 border border-primary row justify-content-center">
               <h6>입력UI</h6>
               <div class="col-auto">
               <select name="=simpleCondition.searchType" class="form-select">
                  <option value>전체</option>
                  <option value="name">이름</option>
                  <option value="address" >주소</option>
               </select>
              </div>
              <div class="col-auto">
               <input name="simpleCondition.searchWord" />
               </div>
              <div class="col-auto">
               <button type="button" data-pg-role="searchBtn" class="btn btn-primary">검색</button>
               </div>
            </div>
			<div id="pagingArea"></div>       
         </td>
      </tr>
   </tfoot>
</table>
<script src="<c:url value='/resources/js/app/common/paging.js' />"></script>
<script>
$(submitForm).on("submit",(event)=>{
	event.preventDefault()
	let form=event.target
	let formData=new FormData(form);
	let qs=new URLSearchParams(formData);
	fetch(form.action+"?"+qs,{
		method:"GET",
		headers:{
			"accept":"application/json"
		}
	}).then(resp=>{
		if(resp.ok){
			return resp.json();
		}else{
			throw new Error("에러 발생",{cause:resp})
		}
	}).then(jsonObj=>{
		console.log(jsonObj)
		pagingArea.innerHTML=jsonObj.pagingHTML;
		let memList=jsonObj.memberList;
		let tbody="";
		if(memList.length>0){
			tbody=memList.map(m=>{
		        let tr = "<tr>";
					tr+=   "<td>"+m.rnum         +"</td>  "
					tr+=   "<td>"+m.memName      +"</td>  "
					tr+=   "<td>"+m.memMail      +"</td>  "
					tr+=   "<td>"+m.memHp        +"</td>  "
					tr+=   "<td>"+m.memAdd1      +"</td>  "
					tr+=   "<td>"+m.memMileage   +"</td>  "
		      		tr+= "</tr>";
		        return tr;
			}).join("\n");
		}else{                                                     
			tbody+="	<tr>                                       "
			tbody+="	  <td colspan='6'>조건에 맞는 회원이 없음</td>   "
			tbody+="    </tr>                                      "
		}                                                          
		
		ttbody.innerHTML=tbody;
		let paging=jsonObj.paging;
	}).catch(err=>console.log(err))
	return false;	
})
	
$(submitForm).submit();
</script>