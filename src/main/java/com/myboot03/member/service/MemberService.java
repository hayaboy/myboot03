package com.myboot03.member.service;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.myboot03.member.vo.MemberVO;

public interface MemberService {
	public List listMembers() throws DataAccessException;
	public int addMember(MemberVO member) throws DataAccessException;
	public void removeMember(String id)throws DataAccessException;
	public MemberVO login(MemberVO memberVO) throws Exception;
	
	
}
