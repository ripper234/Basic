package org.basic.lamba;

public interface Func<TInput, TOutput> {
    TOutput run(TInput input);
}

