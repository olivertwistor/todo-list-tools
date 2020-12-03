package nu.olivertwistor.todolisttools.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public final class SmartAddBuilder
{
    private static final String sign_start = "~";
    private static final String sign_due = "^";
    private static final String sign_repeat = "*";
    private static final String sign_location = "@";
    private static final String sign_priority = "!";
    private static final String sign_list = "#";
    private static final String sign_tag = "#";
    private static final String sign_time_estimate = "=";
    private static final String sign_comment = "//";

    private final String name;
    private String url;
    private String start;
    private String due;
    private String repeat;
    private String location;
    private String priority;
    private String list;
    private final Set<String> tags;
    private String timeEstimate;
    private String comment;

    public SmartAddBuilder(final String name)
    {
        this.name = name;
        this.tags = new HashSet<>();
    }

    public SmartAddBuilder setUrl(final String url)
    {
        this.url = url;
        return this;
    }

    public SmartAddBuilder setStart(final String start)
    {
        this.start = start;
        return this;
    }

    public SmartAddBuilder setDue(final String due)
    {
        this.due = due;
        return this;
    }

    public SmartAddBuilder setRepeat(final String repeat)
    {
        this.repeat = repeat;
        return this;
    }

    public SmartAddBuilder setLocation(final String location)
    {
        this.location = location;
        return this;
    }

    public SmartAddBuilder setPriority(final String priority)
    {
        this.priority = priority;
        return this;
    }

    public SmartAddBuilder setList(final String list)
    {
        this.list = list;
        return this;
    }

    public SmartAddBuilder addTag(final String tag)
    {
        this.tags.add(tag);
        return this;
    }

    public SmartAddBuilder setTimeEstimate(final String timeEstimate)
    {
        this.timeEstimate = timeEstimate;
        return this;
    }

    public SmartAddBuilder setComment(final String comment)
    {
        this.comment = comment;
        return this;
    }

    public String build()
    {
        final StringBuilder out = new StringBuilder(this.name);
        if (this.url != null)
        {
            out.append(" ").append(this.url);
        }
        if (this.start != null)
        {
            out.append(" ").append(sign_start).append(this.start);
        }
        if (this.due != null)
        {
            out.append(" ").append(sign_due).append(this.due);
        }
        if (this.repeat != null)
        {
            out.append(" ").append(sign_repeat).append(this.repeat);
        }
        if (this.location != null)
        {
            out.append(" ").append(sign_location).append(this.location);
        }
        if (this.priority != null)
        {
            out.append(" ").append(sign_priority).append(this.priority);
        }
        if (this.list != null)
        {
            out.append(" ").append(sign_list).append(this.list);
        }
        if (!this.tags.isEmpty())
        {
            for (final String tag : this.tags)
            {
                out.append(" ").append(sign_tag).append(tag);
            }
        }
        if (this.timeEstimate != null)
        {
            out.append(" ").append(sign_time_estimate)
                    .append(this.timeEstimate);
        }
        if (this.comment != null)
        {
            out.append(" ").append(sign_comment).append(this.comment);
        }

        return out.toString();
    }
}
