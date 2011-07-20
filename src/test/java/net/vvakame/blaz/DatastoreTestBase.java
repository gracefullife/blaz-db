package net.vvakame.blaz;

import java.util.ArrayList;
import java.util.List;

import net.vvakame.blaz.Filter.FilterOption;
import net.vvakame.blaz.Filter.FilterTarget;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;

import static org.junit.Assert.*;

/**
 * {@link Datastore} のテスト用クラス.<br>
 * 各 {@link IKeyValueStore} 実装は本クラスを継承し、 {@link #before()} のみ実装すること.
 * @author vvakame
 */
public abstract class DatastoreTestBase {

	/**
	 * {@link Datastore#put(Entity)} に対して対応しているはずの全ての型を突っ込む.
	 * @author vvakame
	 */
	@Test
	public void put_get_single() {
		Key key = KeyUtil.createKey("hoge", "piyo");

		{
			Entity entity = new Entity();
			entity.setKey(key);
			entity.setProperty("null", null);
			entity.setProperty("String", "str");
			entity.setProperty("Byte", (byte) 1);
			entity.setProperty("Short", (short) 2);
			entity.setProperty("Integer", 3);
			entity.setProperty("Long", 4L);
			entity.setProperty("BooleanT", true);
			entity.setProperty("BooleanF", false);
			entity.setProperty("Float", 1.125f);
			entity.setProperty("Double", 2.5);
			entity.setProperty("Key", KeyUtil.createKey("hoge", "puyo"));
			entity.setProperty("byte[]", new byte[] {
				1,
				2,
				3
			});
			entity.setProperty("ListEmpty", new ArrayList<Object>());
			List<Object> list = new ArrayList<Object>();
			list.add(null);
			list.add("str");
			list.add((byte) 1);
			list.add((short) 2);
			list.add(3);
			list.add(4L);
			list.add(true);
			list.add(false);
			list.add(1.125f);
			list.add(2.25);
			list.add(KeyUtil.createKey("hoge", "payo"));
			list.add(new byte[] {
				1,
				2,
				3
			});
			entity.setProperty("List", list);
			Datastore.put(entity);
		}
		{
			Entity entity = Datastore.get(key);
			assertThat(entity.getProperties().size(), is(14));
			assertThat(entity.getProperty("null"), nullValue());
			assertThat(entity.getProperty("String"), is((Object) "str"));
			assertThat(entity.getProperty("Byte"), is((Object) 1L));
			assertThat(entity.getProperty("Short"), is((Object) 2L));
			assertThat(entity.getProperty("Integer"), is((Object) 3L));
			assertThat(entity.getProperty("Long"), is((Object) 4L));
			assertThat(entity.getProperty("BooleanT"), is((Object) true));
			assertThat(entity.getProperty("BooleanF"), is((Object) false));
			assertThat(entity.getProperty("Float"), is((Object) 1.125));
			assertThat(entity.getProperty("Double"), is((Object) 2.5));
			assertThat(entity.getProperty("Key"), is((Object) KeyUtil.createKey("hoge", "puyo")));
			assertThat(entity.getProperty("byte[]"), is((Object) new byte[] {
				1,
				2,
				3
			}));
			List<Object> list;
			list = entity.getProperty("ListEmpty");
			assertThat(list.size(), is(0));
			list = entity.getProperty("List");
			assertThat(list.size(), is(12));
			assertThat(list.get(0), nullValue());
			assertThat(list.get(1), is((Object) "str"));
			assertThat(list.get(2), is((Object) 1L));
			assertThat(list.get(3), is((Object) 2L));
			assertThat(list.get(4), is((Object) 3L));
			assertThat(list.get(5), is((Object) 4L));
			assertThat(list.get(6), is((Object) true));
			assertThat(list.get(7), is((Object) false));
			assertThat(list.get(8), is((Object) 1.125));
			assertThat(list.get(9), is((Object) 2.25));
			assertThat(list.get(10), is((Object) KeyUtil.createKey("hoge", "payo")));
			assertThat(list.get(11), is((Object) new byte[] {
				1,
				2,
				3
			}));
		}
	}

	/**
	 * {@link Datastore#put(Entity)} と {@link Datastore#get(Key)} の動作確認
	 * @author vvakame
	 */
	@Test
	public void put_get_pickup_1() {
		Key key1;
		{
			Entity entity = new Entity();
			key1 = KeyUtil.createKey("hoge", "piyo");
			entity.setKey(key1);
			entity.setProperty("key1", "value1");
			entity.setProperty("key2", "value2");
			entity.setProperty("key3", "value3");
			entity.setProperty("key4", "value4");
			Datastore.put(entity);
		}
		Key key2;
		{
			Entity entity = new Entity();
			key2 = KeyUtil.createKey("hoge", "puyo");
			entity.setKey(key2);
			entity.setProperty("key1", "value1");
			Datastore.put(entity);
		}

		Entity entity;

		entity = Datastore.get(key1);
		assertThat(entity.getProperties().size(), is(4));

		entity = Datastore.get(key2);
		assertThat(entity.getProperties().size(), is(1));
	}

	/**
	 * 動作確認.
	 * @author vvakame
	 */
	@Test
	public void find_string_PROPERTY_EQ_single_filter() {
		{
			Entity entity = new Entity();
			entity.setKey(KeyUtil.createKey("hoge", "piyo1"));
			entity.setProperty("key", "value1");
			Datastore.put(entity);
		}
		{
			Entity entity = new Entity();
			entity.setKey(KeyUtil.createKey("hoge", "piyo2"));
			entity.setProperty("key", "value2");
			Datastore.put(entity);
		}
		{
			Entity entity = new Entity();
			entity.setKey(KeyUtil.createKey("hoge", "piyo3"));
			entity.setProperty("key", "value2");
			Datastore.put(entity);
		}
		List<Entity> list =
				Datastore.find(new Filter(FilterTarget.PROPERTY, "key", FilterOption.EQ, "value2"));
		assertThat(list.size(), is(2));
	}

	/**
	 * 動作確認.
	 * @author vvakame
	 */
	@Test
	public void find_string_PROPERTY_EQ_multi_filter() {
		{
			Entity entity = new Entity();
			entity.setKey(KeyUtil.createKey("hoge", "piyo1"));
			entity.setProperty("name1", "value1");
			entity.setProperty("name2", "value1");
			Datastore.put(entity);
		}
		{
			Entity entity = new Entity();
			entity.setKey(KeyUtil.createKey("hoge", "piyo2"));
			entity.setProperty("name1", "value2");
			entity.setProperty("name2", "value2");
			Datastore.put(entity);
		}
		{
			Entity entity = new Entity();
			entity.setKey(KeyUtil.createKey("hoge", "piyo3"));
			entity.setProperty("name1", "value2");
			entity.setProperty("name2", "value1");
			Datastore.put(entity);
		}
		List<Entity> list =
				Datastore.find(
						new Filter(FilterTarget.PROPERTY, "name1", FilterOption.EQ, "value2"),
						new Filter(FilterTarget.PROPERTY, "name2", FilterOption.EQ, "value1"));
		assertThat(list.size(), is(1));
	}

	/**
	 * 動作確認.
	 * @author vvakame
	 */
	@Test
	public void find_boolean_PROPERTY_EQ_single_filter() {
		{
			Entity entity = new Entity();
			entity.setKey(KeyUtil.createKey("hoge", "piyo1"));
			entity.setProperty("key", true);
			Datastore.put(entity);
		}
		{
			Entity entity = new Entity();
			entity.setKey(KeyUtil.createKey("hoge", "piyo2"));
			entity.setProperty("key", true);
			Datastore.put(entity);
		}
		{
			Entity entity = new Entity();
			entity.setKey(KeyUtil.createKey("hoge", "piyo3"));
			entity.setProperty("key", false);
			Datastore.put(entity);
		}
		List<Entity> list =
				Datastore.find(new Filter(FilterTarget.PROPERTY, "key", FilterOption.EQ, true));
		assertThat(list.size(), is(2));
	}

	/**
	 * 動作確認.
	 * @author vvakame
	 */
	@Test
	public void find_boolean_PROPERTY_EQ_multi_filter() {
		{
			Entity entity = new Entity();
			entity.setKey(KeyUtil.createKey("hoge", "piyo1"));
			entity.setProperty("name1", true);
			entity.setProperty("name2", true);
			Datastore.put(entity);
		}
		{
			Entity entity = new Entity();
			entity.setKey(KeyUtil.createKey("hoge", "piyo2"));
			entity.setProperty("name1", true);
			entity.setProperty("name2", false);
			Datastore.put(entity);
		}
		{
			Entity entity = new Entity();
			entity.setKey(KeyUtil.createKey("hoge", "piyo3"));
			entity.setProperty("name1", false);
			entity.setProperty("name2", false);
			Datastore.put(entity);
		}
		List<Entity> list =
				Datastore.find(new Filter(FilterTarget.PROPERTY, "name1", FilterOption.EQ, true),
						new Filter(FilterTarget.PROPERTY, "name2", FilterOption.EQ, false));
		assertThat(list.size(), is(1));
	}

	/**
	 * {@link Datastore} を呼出し可能なようにセットアップすること.
	 * @author vvakame
	 */
	public abstract void before();
}