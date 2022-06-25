package com.ys.slackclone.navigator

import androidx.navigation.NavOptionsBuilder
import androidx.navigation.navOptions
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class SlackCloneComposeNavigator @Inject constructor() : ComposeNavigator() {

	override fun navigate(route: String, optionsBuilder: (NavOptionsBuilder.() -> Unit)?) {
		val options = optionsBuilder?.let { navOptions(it) }
		navigationCommands.tryEmit(ComposeNavigationCommand.NavigateToRoute(route, options))
	}

	override fun navigateAndClearBackStack(route: String) {
		navigationCommands.tryEmit(
			ComposeNavigationCommand.NavigateToRoute(
				route,
				navOptions {
					popUpTo(0)
				}
			)
		)
	}

	override fun <T> navigateBackWithResult(key: String, result: T, route: String?) {
		navigationCommands.tryEmit(
			ComposeNavigationCommand.NavigateUpWithResult(
				key = key,
				result = result,
				route = route
			)
		)
	}

	override fun popUpTo(route: String, inclusive: Boolean) {
		navigationCommands.tryEmit(ComposeNavigationCommand.PopupToRoute(route, inclusive))
	}

	override fun <T> observeResult(key: String, route: String?): Flow<T> {
		return navControllerFlow
			.filterNotNull()
			.flatMapLatest { navController ->
				val backStackEntry = route?.let { navController.getBackStackEntry(it) }
					?: navController.currentBackStackEntry

				backStackEntry?.savedStateHandle?.let { savedStateHandle ->
					savedStateHandle.getLiveData<T?>(key)
						.asFlow()
						.filter { it != null}
						.onEach {
							// 다시 제출하지 않도록 결과를 무효화
							savedStateHandle.set(key, null)
						}
				} ?: emptyFlow()
			}
	}
}