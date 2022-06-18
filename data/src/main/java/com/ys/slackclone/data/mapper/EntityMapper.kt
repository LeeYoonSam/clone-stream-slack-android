package com.ys.slackclone.data.mapper

interface EntityMapper<Domain, Data> {
	fun mapToDomain(entity: Data): Domain
	fun mapToData(domain: Domain): Data
}