package gec.scf.dummy.common.utils;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

/**
 * An implementation of the {@link PageRequest} which is offset based by means the offset
 * is given and not the page number as in the original {@link PageRequest} implementation
 * where the offset is generated. Due that the REST-API is working with {@code offset} and
 * {@code limit} parameter we need an offset based page request.
 */
public class OffsetBasedPageRequest extends PageRequest {

	private static final long serialVersionUID = 1L;
	private final int offset;

	/**
	 * Creates a new {@link OffsetBasedPageRequest}. Offsets are zero indexed, thus
	 * providing 0 for {@code offset} will return the first entry.
	 * 
	 * @param offset zero-based offset index.
	 * @param limit the limit of the page to be returned.
	 */
	public OffsetBasedPageRequest(final int offset, final int limit) {
		this(offset, limit, null);
	}

	/**
	 * Creates a new {@link OffsetBasedPageRequest}. Offsets are zero indexed, thus
	 * providing 0 for {@code offset} will return the first entry.
	 * 
	 * @param offset zero-based offset index.
	 * @param limit the limit of the page to be returned.
	 * @param sort sort can be {@literal null}.
	 */
	public OffsetBasedPageRequest(final int offset, final int limit, final Sort sort) {
		super(offset, limit, sort);
		this.offset = offset;
	}

	@Override
	public long getOffset() {
		return offset;
	}

	@Override
	public String toString() {
		return "OffsetBasedPageRequest [offset=" + offset + ", getPageSize()="
				+ getPageSize() + ", getPageNumber()=" + getPageNumber() + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + offset;
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (!super.equals(obj)) {
			return false;
		}
		if (!(obj instanceof OffsetBasedPageRequest)) {
			return false;
		}
		final OffsetBasedPageRequest other = (OffsetBasedPageRequest) obj;
		return offset == other.offset;
	}

}
