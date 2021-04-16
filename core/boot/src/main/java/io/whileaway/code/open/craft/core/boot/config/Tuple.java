package io.whileaway.code.open.craft.core.boot.config;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Tuple<K, V> {
    private K key;

    private V value;
}
