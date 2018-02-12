package org.grakovne.mds.publisher.pipeline.step;

import org.grakovne.mds.publisher.pipeline.Context;

public interface Step {
    Context execute(Context context);
}
