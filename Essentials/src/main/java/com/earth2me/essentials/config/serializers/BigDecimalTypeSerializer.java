package com.earth2me.essentials.config.serializers;

import org.checkerframework.checker.nullness.qual.Nullable;
import org.spongepowered.configurate.ConfigurationNode;
import org.spongepowered.configurate.ConfigurationOptions;
import org.spongepowered.configurate.serialize.SerializationException;
import org.spongepowered.configurate.serialize.TypeSerializer;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.MathContext;

public class BigDecimalTypeSerializer implements TypeSerializer<BigDecimal> {
    @Override
    public BigDecimal deserialize(Type type, ConfigurationNode node) throws SerializationException {
        final String value = node.getString();

        if (value == null || value.isEmpty()) {
            return null;
        }

        try {
            return new BigDecimal(value, MathContext.DECIMAL128);
        } catch (final NumberFormatException | ArithmeticException e) {
            throw new SerializationException(e);
        }
    }

    @Override
    public void serialize(Type type, @Nullable BigDecimal value, ConfigurationNode node) throws SerializationException {
        if (value == null) {
            node.set(String.class, "0");
            return;
        }

        node.set(String.class, value.toString());
    }

    @Override
    public @Nullable BigDecimal emptyValue(Type specificType, ConfigurationOptions options) {
        return BigDecimal.ZERO;
    }
}
