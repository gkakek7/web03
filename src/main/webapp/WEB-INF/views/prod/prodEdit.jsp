<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
상품 수정

<form:form method="post" modelAttribute="prod" enctype="multipart/form-data">
<form:input path="prodId"/>
	<table>
		<tr>
			<th>상품명</th>
			<td>
				<form:input path="prodName" class="form-control" />
				<form:errors path="prodName" element="span" cssClass="text-danger"/>
			</td>
		</tr>
<!-- 		<tr> -->
<!-- 			<th>분류</th> -->
<!-- 			<td><select name="prodLgu" disabled> -->
<%-- 					<option value="${prod.lprod.lprodGu }">${prod.lprod.lprodNm }</option> --%>
<%-- 			</select><span class="text-danger">${errors.prodLgu}</span></td> --%>
<!-- 		</tr> -->
<!-- 		<tr> -->
<!-- 			<th>제조사</th> -->
<!-- 			<td><select name="prodBuyer" disabled> -->
<%-- 					<option value="${prod.buyer.buyerId }">${prod.buyer.buyerName}</option> --%>
<%-- 			</select><span class="text-danger">${errors.prodBuyer}</span></td> --%>
<!-- 		</tr> -->
		<tr>
			<th>분류</th>
			<td>
			<form:select path="prodLgu">
				<option value="${prod.lprod.lprodGu }">${prod.lprod.lprodNm }</option>
			</form:select>
			<form:errors path="prodLgu" element="span" cssClass="text-danger" /></td>
		</tr>
		<tr>
			<th>제조사</th>
			<td>
			<form:select path="prodBuyer">
				<option value="${prod.buyer.buyerId }">${prod.buyer.buyerName}</option>
			</form:select>
			<form:errors path="prodBuyer" element="span" cssClass="text-danger" /></td>
		</tr>
		<tr>
			<th>구매가</th>
			<td>
			<form:input type="number" path="prodCost"  cssClass="form-control" />
				<form:errors path="prodCost" element="span" cssClass="text-danger" />
				</td>
		</tr>
		<tr>
			<th>판매가</th>
			<td>
			<form:input type="number" path="prodPrice"  cssClass="form-control" />
				<form:errors path="prodPrice" element="span" cssClass="text-danger" />
				</td>
		</tr>
		<tr>
			<th>세일가</th>
			<td><form:input type="number" path="prodSale"  cssClass="form-control" />
				<form:errors path="prodSale" element="span" cssClass="text-danger" />
				</td>
		</tr>
		<tr>
			<th>요약정보</th>
			<td><form:input path="prodOutline"  cssClass="form-control" />
				<form:errors path="prodOutline" element="span" cssClass="text-danger" />
				</td>
		</tr>
		<tr>
			<th>상세정보</th>
			<td><form:input path="prodDetail"  cssClass="form-control" />
				<form:errors path="prodDetail" element="span" cssClass="text-danger" />
				</td>
		</tr>
		<tr>
			<th>이미지</th>
			<td><form:input type="file" path="prodImage"  cssClass="form-control" />
				<form:errors path="prodImage" element="span" cssClass="text-danger" />
				</td>
		</tr>
		<tr>
			<th>총재고</th>
			<td><form:input path="prodTotalstock"  cssClass="form-control" />
				<form:errors path="prodTotalstock" element="span" cssClass="text-danger" />
				</td>
		</tr>
		<tr>
			<th>적정재고</th>
			<td><form:input path="prodProperstock"  cssClass="form-control" />
				<form:errors path="prodProperstock" element="span" cssClass="text-danger" />
				</td>
		</tr>
		<tr>
			<th>크기</th>
			<td><form:input path="prodSize"  cssClass="form-control" />
				<form:errors path="prodSize" element="span" cssClass="text-danger" />
				</td>
		</tr>
		<tr>
			<th>색상</th>
			<td><form:input path="prodColor"  cssClass="form-control" />
					<form:errors path="prodColor" element="span" cssClass="text-danger" />
				</td>
		</tr>
		<tr>
			<th>배송방법</th>
			<td><form:input path="prodDelivery"  cssClass="form-control" />
				<form:errors path="prodDelivery" element="span" cssClass="text-danger" />
				</td>
		</tr>
		<tr>
			<th>단위</th>
			<td><form:input path="prodUnit"  cssClass="form-control" />
				<form:errors path="prodUnit" element="span" cssClass="text-danger" /></td>
		</tr>
		<tr>
			<th>입고량</th>
			<td><form:input type="number" path="prodQtyin"  cssClass="form-control" />
				<form:errors path="prodQtyin" element="span" cssClass="text-danger" />
				</td>
		</tr>
		<tr>
			<th>출고량</th>
			<td><form:input type="number" path="prodQtysale"  cssClass="form-control" />
				<form:errors path="prodQtysale" element="span" cssClass="text-danger" />
				</td>
		</tr>
		<tr>
			<th>마일리지</th>
			<td><form:input type="number" path="prodMileage"  cssClass="form-control" />
				<form:errors path="prodMileage" element="span" cssClass="text-danger" />
				</td>
		</tr>
		<tr>
			<td colspan="2">
				<input type="submit" value="수정">
			</td>
		</tr>
	</table>
</form:form>
		