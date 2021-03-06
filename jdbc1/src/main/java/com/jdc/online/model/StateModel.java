package com.jdc.online.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.jdc.online.entity.State;
import com.jdc.online.model.api.AbstractModel;
import com.jdc.online.model.api.ConnectionManager;

public class StateModel extends AbstractModel<State> {

	public StateModel() {
		super(State.class);
	}

	public List<State> findByNameLike(String string) {

		List<State> result = new ArrayList<>();
		String sql = "select * from state where name like ?";

		try (Connection conn = ConnectionManager.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setString(1, string.concat("%"));

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				result.add(helper.getData(rs));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

}
