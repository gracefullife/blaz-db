package net.vvakame.blaz.filter;

import net.vvakame.blaz.Filter;

/**
 * Entityを検索するためのフィルタ
 * @author vvakame
 */
public class PropertyStringLtFilter extends AbstractPropertyFilter implements Filter {

	final FilterOption option = FilterOption.LT;

	String value;


	/**
	 * the constructor.
	 * @param name 
	 * @param value 
	 * @category constructor
	 */
	public PropertyStringLtFilter(String name, String value) {
		super(name);
		this.value = value;
	}

	@Override
	public FilterOption getOption() {
		return option;
	}

	@Override
	public String getValue() {
		return value;
	}
}
