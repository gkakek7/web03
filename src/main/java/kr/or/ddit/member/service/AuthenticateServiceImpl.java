package kr.or.ddit.member.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.member.dao.MemberDAO;
import kr.or.ddit.member.exception.AuthenticateException;
import kr.or.ddit.vo.MemberVO;

@Service
public class AuthenticateServiceImpl implements AuthenticateService{
	@Inject
	private MemberDAO dao;
	@Override
	public MemberVO authenticate(MemberVO inputData) {
		boolean auth=false;
		MemberVO saved = dao.selectMemberForAuth(inputData.getMemId());
		if(saved!=null) {
			String inputPass=inputData.getMemPass();
			String savedPass=saved.getMemPass();
			auth=inputPass.equals(savedPass);
		}
		if(!auth) throw new AuthenticateException(inputData.getMemId());
		return saved;
	}
}
