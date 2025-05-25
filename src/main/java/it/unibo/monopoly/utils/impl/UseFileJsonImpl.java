package it.unibo.monopoly.utils.impl;


import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;

import java.util.List;
import java.util.Objects;
import java.util.Set;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;

import it.unibo.monopoly.model.transactions.api.TitleDeed;
import it.unibo.monopoly.model.transactions.impl.BaseTitleDeed;

import it.unibo.monopoly.utils.api.UseFileJson;


/**
 * Implementation of {@link UseFileJson} responsible for deserializing JSON files
 * into Java collections using Jackson.
 * <p>
 * Relies on a shared {@link ObjectMapper} and type-safe deserialization strategy.
 */
public final class UseFileJsonImpl extends AbstractUseFileImpl implements UseFileJson {

    private final ObjectMapper mapper = new ObjectMapper()
                                            .registerModule(new Jdk8Module());

    /**
     * {@inheritDoc}
     */
    @Override
    public <T> List<T> loadJsonList(final String path, final Class<T> type) {
        Objects.requireNonNull(type, "The type of deserialization must not be null");
        final List<T> out;
        try (InputStream fileJson = getRequiredStream(path)) {
            final JavaType outType = mapper.getTypeFactory()
                    .constructCollectionLikeType(List.class, type);
            out = mapper.readValue(fileJson, outType);
        } catch (final IOException e) {
            throw new UncheckedIOException("Failed to convert the Json file '" + path + "'", e);
        }
        return out;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<TitleDeed> loadTitleDeeds(final String path) {
        return Set.copyOf(loadJsonList(path, BaseTitleDeed.class));
    }

}
