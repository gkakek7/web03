package kr.or.ddit.vo;

import java.util.List;
import java.util.Map;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import kr.or.ddit.validate.DeleteGroup;
import kr.or.ddit.validate.InsertGroup;
import kr.or.ddit.validate.UpdateGroup;
import kr.or.ddit.validate.constraints.TelNumber;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of="buyerId")
public class BuyerVO {
   private int rnum;
   @NotBlank(groups = {UpdateGroup.class,InsertGroup.class})
   private String buyerId;
   @NotBlank
   private String buyerName;
   @NotBlank
   private String buyerLgu;
   private String buyerBank;
   private String buyerBankno;
   private String buyerBankname;
   private String buyerZip;
   private String buyerAdd1;
   private String buyerAdd2;
   @NotBlank
   @TelNumber
   private String buyerComtel;
   @NotBlank
   private String buyerFax;
   @NotBlank
   @Email
   private String buyerMail;
   private String buyerCharger;
   private String buyerTelext;
   private String buyerTelext2;
   private String buyerMail2;
   private String buyerCharger2;
   
   // Has A 관계 - 1:1 -> association
   private Map<String, Object> lprod;
   
   // Has Many 관계 - 1:N -> collection
   private List<ProdVO> prodList; // 제조사와의 거래품목
}


