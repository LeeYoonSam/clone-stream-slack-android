package com.ys.slackclone.domain.usecases

import kotlinx.coroutines.flow.Flow

interface BaseUseCase<out Result, in ExecutableParam> {

	/**
	 * 입력 매개변수 없이 작업을 수행합니다.
	 * 구현되지 않았지만 호출된 경우 기본적으로 예외가 발생합니다.
	 */
	suspend fun perform(): Result = throw NotImplementedError()

	/**
	 * 작업을 수행합니다.
	 * 구현되지 않았지만 호출된 경우 기본적으로 예외가 발생합니다.
	 */
	suspend fun perform(params: ExecutableParam): Result? = throw NotImplementedError()

	/**
	 * Flow를 반환하는 작업을 수행합니다.
	 * 구현되지 않았지만 호출된 경우 기본적으로 예외가 발생합니다.
	 */
	fun performStreaming(params: ExecutableParam?): Flow<Result> = throw NotImplementedError()
}