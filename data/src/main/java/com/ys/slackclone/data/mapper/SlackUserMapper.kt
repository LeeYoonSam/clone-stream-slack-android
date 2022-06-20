package com.ys.slackclone.data.mapper

import com.github.vatbub.randomusers.result.Name
import com.github.vatbub.randomusers.result.RandomUser
import com.ys.slackclone.domain.model.users.DomainLayerUsers
import javax.inject.Inject

class SlackUserMapper @Inject constructor() :
	EntityMapper<DomainLayerUsers.SlackUser, RandomUser> {

	override fun mapToDomain(entity: RandomUser): DomainLayerUsers.SlackUser {
		return DomainLayerUsers.SlackUser(
			gender = entity.gender.genderText,
			name = entity.name.fullName(),
			location = entity.location.city,
			email = entity.email,
			login = entity.login.username,
			dateOfBirth = entity.dateOfBirth.time,
			registrationDate = entity.registrationDate.time,
			phone = entity.phone,
			cell = entity.cell,
			picture = entity.picture.mediumPicture.toURI().toString(),
			nationality = entity.nationality.shortCode
		)
	}

	override fun mapToData(domain: DomainLayerUsers.SlackUser): RandomUser {
		TODO("Not yet implemented")
	}
}

private fun Name.fullName(): String {
	return "{${this.firstName} ${this.lastName}}"
}