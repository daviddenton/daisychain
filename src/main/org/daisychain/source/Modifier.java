package org.daisychain.source;

import org.daisychain.source.util.IndentingStringWriter;
import org.daisychain.source.util.ListAppender;
import static org.daisychain.source.util.ListAppender.generateSource;
import static org.daisychain.source.util.ListAppender.loop;

import java.io.IOException;
import java.util.List;

public enum Modifier implements SourceGenerator {
    Private, Protected, Public, Static, Abstract, Final;

    public static final Modifier[] publicStatic = new Modifier[] {Public, Static};
    public static final Modifier[] publicStaticFinal = new Modifier[] {Public, Static, Final};
    public static final Modifier[] publicFinal = new Modifier[] {Public, Final};
    public static final Modifier[] privateFinal = new Modifier[] {Private, Final};


    public void appendSource(IndentingStringWriter writer) throws IOException {
        writer.append(name().toLowerCase());
    }

    public static ListAppender<Modifier> appendModifiersSourceOf(List<Modifier> modifiers) {
        return loop(modifiers).spaceSeperated().andForEach(generateSource()).withSuffix(" ");
    }
}
