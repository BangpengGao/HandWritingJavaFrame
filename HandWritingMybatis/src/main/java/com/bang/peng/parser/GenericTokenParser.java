package com.bang.peng.parser;

import com.bang.peng.handler.TokenHandler;

/*
 * @ Created with IntelliJ IDEA
 * @ Author Bangpeng Gao
 * @ Version 1.0.0
 * @ Since 1.0.0
 * @ Date 2021/7/16
 * @ Time 16:08
 */
public class GenericTokenParser {

    private final String openToken;
    private final String closeToken;
    private final TokenHandler handler;

    public GenericTokenParser(String openToken, String closeToken, TokenHandler handler) {
        this.openToken = openToken;
        this.closeToken = closeToken;
        this.handler = handler;
    }

    public String getOpenToken() {
        return openToken;
    }

    public String getCloseToken() {
        return closeToken;
    }

    public TokenHandler getHandler() {
        return handler;
    }

    @Override
    public String toString() {
        return "GenericTokenParser{" +
                "openToken='" + openToken + '\'' +
                ", closeToken='" + closeToken + '\'' +
                ", handler=" + handler +
                '}';
    }

    public String parse(String text) {
        if (null == text || text.isEmpty())
            return "";

        int start = text.indexOf(openToken, 0);
        if (start == -1)
            return text;

        char[] chars = text.toCharArray();
        int offset = 0;
        final StringBuilder builder = new StringBuilder();
        StringBuilder tokenBuilder = null;
        while (start > -1) {
            if (start > 0 && chars[start - 1] == '\\') {
                builder.append(chars, offset, start - offset - 1).append(openToken);
                offset = start + openToken.length();
            } else {
                if (null == tokenBuilder)
                    tokenBuilder = new StringBuilder();
                else
                    tokenBuilder.setLength(0);

                builder.append(chars, offset, start - offset);
                offset = start + openToken.length();
                int end = text.indexOf(closeToken, offset);
                while (end > -1) {
                    if (end > offset && chars[end - 1] == '\\') {
                        tokenBuilder.append(chars, offset, end - offset - 1).append(closeToken);
                        offset = end + closeToken.length();
                        end = text.indexOf(closeToken, offset);
                    } else {
                        tokenBuilder.append(chars, offset, end - offset);
                        offset = end + closeToken.length();
                        break;
                    }
                }
                if (-1 == end) {
                    builder.append(chars, start, chars.length - start);
                    offset = chars.length;
                } else {
                    builder.append(handler.handleToken(tokenBuilder.toString()));
                    offset = end + closeToken.length();
                }
            }
            start = text.indexOf(openToken, offset);
        }
        if (offset < chars.length) {
            builder.append(chars, offset, chars.length - offset);
        }
        return builder.toString();
    }
}
