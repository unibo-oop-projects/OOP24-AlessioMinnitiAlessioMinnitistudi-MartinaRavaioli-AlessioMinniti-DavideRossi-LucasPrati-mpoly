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
 * Implementation of {@link UseFileJson} for loading JSON arrays from resources.
 * <p>
 * This class uses Jackson's {@link ObjectMapper} for deserialization.
 * It supports generic types and automatically registers the {@link Jdk8Module}
 * to handle optional and JDK8+ features.
 */
public class UseFileJsonImpl extends AbstractUseFileImpl implements UseFileJson{

    private final ObjectMapper MAPPER = new ObjectMapper()
                                            .registerModule(new Jdk8Module());

    /**
     * {@inheritDoc}
     */
    @Override
    public <T> List<T> loadJsonList(String path, Class<T> type) {
        Objects.requireNonNull(type);
        final List<T> out;
        try (InputStream fileJson = getRequiredStream(path)) {
            final JavaType outType = MAPPER.getTypeFactory()
                    .constructCollectionLikeType(List.class, type);
            out = MAPPER.readValue(fileJson, outType);
        } catch (final IOException e) {
            throw new UncheckedIOException("Failed to convert the Json file '" + path + "': ", e);
        }
        return out;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<TitleDeed> loadTitleDeeds(String path) {
        return Set.copyOf(loadJsonList(path, BaseTitleDeed.class));
    }
    
}
