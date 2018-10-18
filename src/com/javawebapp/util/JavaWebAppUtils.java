package com.javawebapp.util;

import java.util.UUID;

public class JavaWebAppUtils {
	/**
	 * as per wikipedia:
	 * https://en.wikipedia.org/wiki/Universally_unique_identifier#Random_UUID_probability_of_duplicates
	 * [A]fter generating 1 billion UUIDs every second for the next 100 years, the probability of creating just one duplicate would be about 50%
	 * 
	 * Ours won't be that secure since UUID is 128 bit and long is only 64 bit, but this is good enough for now
	 * 
	 * @return an instance of UUID as a Long
	 */
	public static Long generateUniqueId()
	{
		return UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;
	}
}
