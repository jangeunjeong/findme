package com.ssafy.findme.mapper;


public interface EntityMapper {
	public <D,E> D convertToDomain(E source,Class<? extends D> classLiteral);
}
