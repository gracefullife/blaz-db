package net.vvakame.blaz.filter;


/**
 * Entityを検索するためのフィルタ
 * 
 * @author vvakame
 */
public class PropertyBooleanLtFilter extends AbstractPropertyFilter {

	final FilterOption option = FilterOption.LT;

	boolean value;

	/**
	 * the constructor.
	 * 
	 * @param name
	 * @param value
	 * @category constructor
	 */
	public PropertyBooleanLtFilter(String name, boolean value) {
		super(name);
		this.value = value;
	}

	@Override
	public FilterOption getOption() {
		return option;
	}

	@Override
	public Boolean getValue() {
		return value;
	}
}
