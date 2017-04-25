MERGE INTO users u
USING (VALUES ('user', 'password', TRUE)) AS vals(x, y, z)
ON u.username = vals.x
WHEN MATCHED THEN UPDATE SET u.password = vals.y, u.enabled = vals.z
WHEN NOT MATCHED THEN INSERT VALUES vals.x, vals.y, vals.z;

MERGE INTO authorities au
USING (VALUES ('user', 'USER')) AS vals(x, y)
ON au.username = vals.x
WHEN MATCHED THEN UPDATE SET au.authority = vals.y
WHEN NOT MATCHED THEN INSERT VALUES vals.x, vals.y;

MERGE INTO users u
USING (VALUES ('admin', 'password', TRUE)) AS vals(x, y, z)
ON u.username = vals.x
WHEN MATCHED THEN UPDATE SET u.password = vals.y, u.enabled = vals.z
WHEN NOT MATCHED THEN INSERT VALUES vals.x, vals.y, vals.z;

MERGE INTO authorities au
USING (VALUES ('admin', 'ADMIN')) AS vals(x, y)
ON au.username = vals.x
WHEN MATCHED THEN UPDATE SET au.authority = vals.y
WHEN NOT MATCHED THEN INSERT VALUES vals.x, vals.y;

MERGE INTO users u
USING (VALUES ('approver', 'password', TRUE)) AS vals(x, y, z)
ON u.username = vals.x
WHEN MATCHED THEN UPDATE SET u.password = vals.y, u.enabled = vals.z
WHEN NOT MATCHED THEN INSERT VALUES vals.x, vals.y, vals.z;

MERGE INTO authorities au
USING (VALUES ('approver', 'approver')) AS vals(x, y)
ON au.username = vals.x
WHEN MATCHED THEN UPDATE SET au.authority = vals.y
WHEN NOT MATCHED THEN INSERT VALUES vals.x, vals.y;
