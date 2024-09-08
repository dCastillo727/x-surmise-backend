package com.xsurmise.authorizationdata.layers.infrastructure.utils.jpa;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.usertype.UserType;

import java.io.Serializable;
import java.sql.*;
import java.util.Arrays;

public class CustomStringArrayType implements UserType<String[]> {

    @Override
    public int getSqlType() {
        return Types.ARRAY;
    }

    @Override
    public Class<String[]> returnedClass() {
        return String[].class;
    }

    @Override
    public boolean equals(String[] strings, String[] j1) {
        return Arrays.equals(strings, j1);
    }

    @Override
    public int hashCode(String[] strings) {
        return Arrays.hashCode(strings);
    }

    @Override
    public String[] nullSafeGet(
            ResultSet resultSet,
            int position,
            SharedSessionContractImplementor sharedSessionContractImplementor,
            Object owner
    ) throws SQLException {
        Array array = resultSet.getArray(position);
        return array != null ? (String[]) array.getArray() : null;
    }

    @Override
    public void nullSafeSet(
            PreparedStatement preparedStatement,
            String[] strings,
            int position,
            SharedSessionContractImplementor sharedSessionContractImplementor
    ) throws SQLException {
        if (preparedStatement != null) {
            if (strings == null) preparedStatement.setArray(position, null);

            Array array = sharedSessionContractImplementor
                    .getJdbcConnectionAccess()
                    .obtainConnection()
                    .createArrayOf("varchar", strings);
            preparedStatement.setArray(position, array);
        }
    }

    @Override
    public String[] deepCopy(String[] strings) {
        return Arrays.copyOf(strings, strings.length);
    }

    @Override
    public boolean isMutable() {
        return false;
    }

    @Override
    public Serializable disassemble(String[] strings) {
        return null;
    }

    @Override
    public String[] assemble(Serializable serializable, Object o) {
        return new String[0];
    }
}
