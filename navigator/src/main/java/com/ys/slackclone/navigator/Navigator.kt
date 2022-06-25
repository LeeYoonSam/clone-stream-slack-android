package com.ys.slackclone.navigator

import androidx.navigation.NavController
import androidx.navigation.NavOptionsBuilder
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onSubscription

abstract class Navigator {
	val navigationCommands = MutableSharedFlow<NavigationCommand>(extraBufferCapacity = Int.MAX_VALUE)

	// 여기서 StateFlow를 사용하여 ViewModel이 초기 구성 전에 탐색 결과 관찰을 시작하고 나중에 탐색 결과를 얻을 수 있도록 합니다.
	val navControllerFlow = MutableStateFlow<NavController?>(null)

	fun navigateUp() {
		navigationCommands.tryEmit(NavigationCommand.NavigateUp)
	}
}

abstract class ComposeNavigator : Navigator() {
	abstract fun navigate(route: String, optionsBuilder: (NavOptionsBuilder.() -> Unit)? = null)
	abstract fun <T> observeResult(key: String, route: String? = null): Flow<T>
	abstract fun <T> navigateBackWithResult(key: String, result: T, route: String?)

	abstract fun popUpTo(route: String, inclusive: Boolean)
	abstract fun navigateAndClearBackStack(route: String)

	suspend fun handleNavigationCommands(navController: NavController) {
		navigationCommands
			.onSubscription { this@ComposeNavigator.navControllerFlow.value = navController }
			.onCompletion { this@ComposeNavigator.navControllerFlow.value = null }
			.collect { navController.handleComposeNavigationCommand(it) }

	}

	private fun NavController.handleComposeNavigationCommand(navigationCommand: NavigationCommand) {
		when (navigationCommand) {
			is ComposeNavigationCommand.NavigateToRoute -> {
				navigate(navigationCommand.route, navigationCommand.options)
			}
			is ComposeNavigationCommand.PopupToRoute -> popBackStack(
				navigationCommand.route,
				navigationCommand.inclusive
			)
			is ComposeNavigationCommand.NavigateUpWithResult<*> -> {
				navUpWithResult(navigationCommand)
			}
			NavigationCommand.NavigateUp -> navigateUp()
		}
	}

	private fun NavController.navUpWithResult(navigationCommand: ComposeNavigationCommand.NavigateUpWithResult<*>) {
		val backStackEntry =
			navigationCommand.route?.let { getBackStackEntry(it) }
				?: previousBackStackEntry
		backStackEntry?.savedStateHandle?.set(
			navigationCommand.key,
			navigationCommand.result
		)

		navigationCommand.route?.let {
			popBackStack(it, false)
		} ?: run {
			navigateUp()
		}
	}
}