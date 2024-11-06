package com.appstory.data.database;

import android.database.Cursor;
import androidx.paging.PagingSource;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.paging.LimitOffsetPagingSource;
import androidx.room.util.CursorUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.appstory.data.network.respons.ListStoryItem;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Integer;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class StoriesDao_Impl implements StoriesDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<ListStoryItem> __insertionAdapterOfListStoryItem;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAll;

  public StoriesDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfListStoryItem = new EntityInsertionAdapter<ListStoryItem>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `stories` (`id`,`photoUrl`,`createdAt`,`name`,`description`,`lon`,`lat`) VALUES (?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, ListStoryItem value) {
        if (value.getId() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getId());
        }
        if (value.getPhotoUrl() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getPhotoUrl());
        }
        if (value.getCreatedAt() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getCreatedAt());
        }
        if (value.getName() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getName());
        }
        if (value.getDescription() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getDescription());
        }
        stmt.bindDouble(6, value.getLon());
        stmt.bindDouble(7, value.getLat());
      }
    };
    this.__preparedStmtOfDeleteAll = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM stories";
        return _query;
      }
    };
  }

  @Override
  public Object insertAll(final List<ListStoryItem> story,
      final Continuation<? super Unit> continuation) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfListStoryItem.insert(story);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, continuation);
  }

  @Override
  public Object deleteAll(final Continuation<? super Unit> continuation) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAll.acquire();
        __db.beginTransaction();
        try {
          _stmt.executeUpdateDelete();
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
          __preparedStmtOfDeleteAll.release(_stmt);
        }
      }
    }, continuation);
  }

  @Override
  public PagingSource<Integer, ListStoryItem> getAllStory() {
    final String _sql = "SELECT * FROM stories";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return new LimitOffsetPagingSource<ListStoryItem>(_statement, __db, "stories") {
      @Override
      protected List<ListStoryItem> convertRows(Cursor cursor) {
        final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(cursor, "id");
        final int _cursorIndexOfPhotoUrl = CursorUtil.getColumnIndexOrThrow(cursor, "photoUrl");
        final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(cursor, "createdAt");
        final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(cursor, "name");
        final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(cursor, "description");
        final int _cursorIndexOfLon = CursorUtil.getColumnIndexOrThrow(cursor, "lon");
        final int _cursorIndexOfLat = CursorUtil.getColumnIndexOrThrow(cursor, "lat");
        final List<ListStoryItem> _result = new ArrayList<ListStoryItem>(cursor.getCount());
        while(cursor.moveToNext()) {
          final ListStoryItem _item;
          final String _tmpId;
          if (cursor.isNull(_cursorIndexOfId)) {
            _tmpId = null;
          } else {
            _tmpId = cursor.getString(_cursorIndexOfId);
          }
          final String _tmpPhotoUrl;
          if (cursor.isNull(_cursorIndexOfPhotoUrl)) {
            _tmpPhotoUrl = null;
          } else {
            _tmpPhotoUrl = cursor.getString(_cursorIndexOfPhotoUrl);
          }
          final String _tmpCreatedAt;
          if (cursor.isNull(_cursorIndexOfCreatedAt)) {
            _tmpCreatedAt = null;
          } else {
            _tmpCreatedAt = cursor.getString(_cursorIndexOfCreatedAt);
          }
          final String _tmpName;
          if (cursor.isNull(_cursorIndexOfName)) {
            _tmpName = null;
          } else {
            _tmpName = cursor.getString(_cursorIndexOfName);
          }
          final String _tmpDescription;
          if (cursor.isNull(_cursorIndexOfDescription)) {
            _tmpDescription = null;
          } else {
            _tmpDescription = cursor.getString(_cursorIndexOfDescription);
          }
          final double _tmpLon;
          _tmpLon = cursor.getDouble(_cursorIndexOfLon);
          final double _tmpLat;
          _tmpLat = cursor.getDouble(_cursorIndexOfLat);
          _item = new ListStoryItem(_tmpId,_tmpPhotoUrl,_tmpCreatedAt,_tmpName,_tmpDescription,_tmpLon,_tmpLat);
          _result.add(_item);
        }
        return _result;
      }
    };
  }

  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
