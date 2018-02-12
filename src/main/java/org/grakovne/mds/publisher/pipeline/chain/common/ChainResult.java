package org.grakovne.mds.publisher.pipeline.chain.common;

public class ChainResult {
    private ChainResultType result;
    private String description;

    public ChainResult(ChainResultType result, String description) {
        this.result = result;
        this.description = description;
    }

    public ChainResult(ChainResultType result) {
        this.result = result;
    }

    public ChainResultType getResult() {
        return result;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "ChainResult{" +
            "result=" + result +
            ", description='" + description + '\'' +
            '}';
    }
}
