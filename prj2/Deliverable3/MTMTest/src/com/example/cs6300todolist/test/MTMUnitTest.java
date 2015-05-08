package com.example.cs6300todolist.test;

import android.app.Activity;
import android.database.Cursor;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.cs6300todolist.R;
import com.example.cs6300todolist.MainActivity;
import com.example.database.MySQLiteHelper;
import com.example.database.ToDoItem;
import com.example.database.ToDoItemDataSource;
import com.example.database.UserDataSource;
import com.example.database.Users;

public class MTMUnitTest extends
		ActivityInstrumentationTestCase2<MainActivity> {

	public MTMUnitTest() {
		super(MainActivity.class);
	}

	private Activity activity;
	private UserDataSource userData;
	private ToDoItemDataSource todoItem;

	@Override
	protected void setUp() throws Exception {
		activity = getActivity();
		userData = new UserDataSource(activity);
		todoItem = new ToDoItemDataSource(activity);
		super.setUp();
	}

	@Override
	protected void tearDown() throws Exception {
		try {
			super.tearDown();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void testPreconditions() {
		assertNotNull("activity is null", activity);
	}

	public void testCreateUser() {
		userData.open();
		Users newUser = userData.createUsers("testUser", "pwd");
		assertEquals("Test user was not created", newUser.getName(), "testUser");
		userData.deleteUser(newUser);
		userData.close();
	}

	public void testDeleteUser() {
		userData.open();
		Users user = userData.createUsers("testUserDelete", "pwd");
		userData.deleteUser(user);
		user = userData.selectUser("testUserDelete");
		// assert the deleted user is not present
		assertNull("Test user was not deleted", user);
		userData.close();
	}

	public void testCreateItem() {
		todoItem.open();
		userData.open();
		Users user = userData.createUsers("testItem", "pwd");
		ToDoItem item = todoItem.createItem(user.getId(), "task", "note", 1,
				true, true, 1);
		assertEquals("Test task was not created", item.getName(), "task");
		todoItem.deleteItem(item);
		userData.close();
		todoItem.close();
	}

	public void testDeleteItem() {
		todoItem.open();
		userData.open();
		Users user = userData.createUsers("testItemDeletion", "pwd");
		ToDoItem item = todoItem.createItem(user.getId(), "task", "note", 1,
				true, true, 1);
		todoItem.deleteItem(item);
		item = todoItem.getItemByItemId(item.getId());
		assertNull("Test task was not deleted.", item);
		userData.close();
		todoItem.close();
	}

	public void testCheckItem() {
		userData.open();
		todoItem.open();
		Users user = userData.createUsers("testCheck", "pwd");
		ToDoItem item = todoItem.createItem(user.getId(), "task", "note", 1,
				true, false, 1);
		item.setChecked(true);
		assertTrue("Test task was not marked as completed.", item.isChecked());
		userData.deleteUser(user);
		todoItem.deleteItem(item);
		userData.close();
		todoItem.close();

	}

	public void testUnCheckItem() {
		userData.open();
		todoItem.open();
		Users user = userData.createUsers("testCheck", "pwd");
		ToDoItem item = todoItem.createItem(user.getId(), "task", "note", 1,
				true, true, 1);
		item.setChecked(false);
		assertFalse(item.isChecked());
		userData.deleteUser(user);
		todoItem.deleteItem(item);
		userData.close();
		todoItem.close();

	}

	public void testUpdateUser() {
		userData.open();
		Users user = userData.createUsers("UserUpdate", "pwd");
		user.setPwd("pwd2");
		userData.updateUsers(user);
		Users newUser = userData.selectUser("UserUpdate");
		assertEquals("Test user information was not updated.", newUser.getPwd(), "pwd2");
		userData.deleteUser(user);
		userData.close();
	}

	public void testUpdateItem() {
		userData.open();
		todoItem.open();
		Users user = userData.createUsers("ItemUpdate", "pwd");
		ToDoItem item = todoItem.createItem(user.getId(), "task", "note", 1,
				true, true, 1);
		item.setNote("updateNote");
		todoItem.updateItem(item);
		ToDoItem newitem = todoItem.getItemByItemId(item.getId());
		assertEquals("Test task was not updated.", newitem.getNote(), "updateNote");
		userData.deleteUser(user);
		userData.close();
		todoItem.close();
	}
}
