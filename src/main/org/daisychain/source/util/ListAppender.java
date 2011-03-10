package org.daisychain.source.util;

import org.daisychain.source.SourceGenerator;

import java.io.IOException;
import java.util.List;

public abstract class ListAppender<T> {

    ItemContent<T> content = new ItemContent<T>() {
        public void append(IndentingStringWriter writer, T target) {
        }
    };

    DynamicContent seperator = empty();
    DynamicContent prefix = empty();
    DynamicContent suffix = empty();

    public static <T> ListAppender<T> loopWrapped(final List<T> items) {
        return new ListAppender<T>() {
            public void to(IndentingStringWriter writer) throws IOException {
                prefix.append(writer);
                for (int i = 0; i < items.size(); i++) {
                    if (i > 0) seperator.append(writer);
                    content.append(writer, items.get(i));
                }
                suffix.append(writer);
            }
        };
    }

    public static <T> ListAppender<T> loop(final List<T> items) {
        return new ListAppender<T>() {
            public void to(IndentingStringWriter writer) throws IOException {
                if(!items.isEmpty()) prefix.append(writer);
                for (int i = 0; i < items.size(); i++) {
                    if (i > 0) seperator.append(writer);
                    content.append(writer, items.get(i));
                }
                if(!items.isEmpty()) suffix.append(writer);
            }
        };
    }

    public ListAppender<T> withPrefix(String prefix) {
        this.prefix = of(prefix);
        return this;
    }

    public ListAppender<T> withPrefix(DynamicContent prefix) {
        this.prefix = prefix;
        return this;
    }

    public ListAppender<T> andForEach(ItemContent<T> content) {
        this.content = content;
        return this;
    }

    public ListAppender<T> newLineSeperated() {
        return seperatedBy(of("\n"));
    }

    public ListAppender<T> doubleLineSeperated() {
        return seperatedBy(of("\n\n"));
    }

    public ListAppender<T> spaceSeperated() {
        return seperatedBy(of(" "));
    }

    public ListAppender<T> commaSeperated() {
        return seperatedBy(of(", "));
    }

    public ListAppender<T> seperatedBy(String seperator) {
        this.seperator = of(seperator);
        return this;
    }

    public ListAppender<T> seperatedBy(DynamicContent seperator) {
        this.seperator = seperator;
        return this;
    }

    public ListAppender<T> withSuffix(String suffix) {
        this.suffix = of(suffix);
        return this;
    }

    public ListAppender<T> withSuffix(DynamicContent suffix) {
        this.suffix = suffix;
        return this;
    }

    public abstract void to(IndentingStringWriter writer) throws IOException;

    public static DynamicContent empty() {
        return new DynamicContent() {
            public void append(IndentingStringWriter writer) {
            }
        };
    }

    public static <T extends SourceGenerator> ItemContent generateSource() {
        return new ItemContent<T>() {
            public void append(IndentingStringWriter writer, T target) throws IOException {
                target.appendSource(writer);
            }
        };
    }

    public static DynamicContent of(final String string) {
        return new DynamicContent() {
            public void append(IndentingStringWriter writer) throws IOException {
                writer.append(string);
            }
        };
    }

    public static <T> void method(List<T> parameters, ItemContent<T> content, IndentingStringWriter writer) throws IOException {
        loopWrapped(parameters).withPrefix(of("(")).commaSeperated().andForEach(content).withSuffix(of(")")).to(writer);
    }
}
