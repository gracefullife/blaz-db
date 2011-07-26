package net.vvakame.blaz.util;

import net.vvakame.blaz.Filter;
import net.vvakame.blaz.filter.KeyFilter;
import net.vvakame.blaz.filter.KindFilter;

/**
 * 発行可能な {@link Filter} の組み合わせについてチェックするユーティリティ.
 * @author vvakame
 */
public class FilterChecker {

	private FilterChecker() {
	}

	/**
	 * 組み合わせのチェックを行う.<br>
	 * 汎用的なKVSを想定する場合、このメソッドで {@code false} が返った場合、そのフィルタは適用しないようにするべき.<br>
	 * バックヤードのKVSを固定する場合はその限りではない.
	 * @param filters
	 * @return クエリ発行可否
	 * @author vvakame
	 */
	public static boolean check(Filter... filters) {
		if (filters.length == 0) {
			return true;
		}
		{ // KindFilter
			if (hasKindFilter(filters)) {
				int count = 0;
				for (Filter filter : filters) {
					if (filter instanceof KindFilter) {
						count++;
					}
				}
				if (1 < count) {
					return false;
				}
			}
		}
		{ // KeyFilter
			if (hasKeyFilter(filters)) {
				for (Filter filter : filters) {
					if (filter instanceof KeyFilter == false) {
						return false;
					}
				}
			}
		}

		return true;
	}

	static boolean hasKindFilter(Filter... filters) {
		for (Filter filter : filters) {
			if (filter instanceof KindFilter) {
				return true;
			}
		}
		return false;
	}

	static boolean hasKeyFilter(Filter... filters) {
		for (Filter filter : filters) {
			if (filter instanceof KeyFilter) {
				return true;
			}
		}
		return false;
	}
}