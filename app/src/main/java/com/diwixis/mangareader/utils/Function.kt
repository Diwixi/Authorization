package com.diwixis.mangareader.utils

/**
 * Группа Функциональных интерфейсов (коллбеки)
 *
 * @author П. Густокашин (Diwixis)
 */
/** возвращает значение. */
typealias Supplier<T> = () -> T

/** возвращает `true` или `false` для заданного входного значения. */
typealias Predicate<T> = (T) -> Boolean

/** принимает одно значение. */
typealias Consumer<T> = (T) -> Unit

/** не имеет входного и выходного значения. */
typealias Action = () -> Unit